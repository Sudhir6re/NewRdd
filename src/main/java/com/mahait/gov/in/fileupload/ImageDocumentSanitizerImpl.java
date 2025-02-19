package com.mahait.gov.in.fileupload;
import org.apache.commons.imaging.ImageInfo;
import org.apache.commons.imaging.ImageParser;
import org.apache.commons.imaging.Imaging;
import org.apache.commons.imaging.formats.bmp.BmpImageParser;
import org.apache.commons.imaging.formats.dcx.DcxImageParser;
import org.apache.commons.imaging.formats.gif.GifImageParser;
import org.apache.commons.imaging.formats.pcx.PcxImageParser;
import org.apache.commons.imaging.formats.png.PngImageParser;
import org.apache.commons.imaging.formats.tiff.TiffImageParser;
import org.apache.commons.imaging.formats.wbmp.WbmpImageParser;
import org.apache.commons.imaging.formats.xbm.XbmImageParser;
import org.apache.commons.imaging.formats.xpm.XpmImageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;

/* Added by Sudhir */
public class ImageDocumentSanitizerImpl implements DocumentSanitizer {

   
    private static final Logger LOG = LoggerFactory.getLogger(ImageDocumentSanitizerImpl.class);


    @Override
    public boolean madeSafe(File f) {
        boolean safeState = false;
        boolean fallbackOnApacheCommonsImaging;
        try {
            if ((f != null) && f.exists() && f.canRead() && f.canWrite()) {
                String formatName;
                try (ImageInputStream iis = ImageIO.createImageInputStream(f)) {
                    Iterator<ImageReader> imageReaderIterator = ImageIO.getImageReaders(iis);
                    if (!imageReaderIterator.hasNext()) {
                        ImageInfo imageInfo = Imaging.getImageInfo(f);
                        if (imageInfo != null && imageInfo.getFormat() != null && imageInfo.getFormat().getName() != null) {
                            formatName = imageInfo.getFormat().getName();
                            fallbackOnApacheCommonsImaging = true;
                        } else {
                            throw new IOException("Format of the original image is not supported for read operation !");
                        }
                    } else {
                        ImageReader reader = imageReaderIterator.next();
                        formatName = reader.getFormatName();
                        fallbackOnApacheCommonsImaging = false;
                    }
                }

                BufferedImage originalImage;
                if (!fallbackOnApacheCommonsImaging) {
                    originalImage = ImageIO.read(f);
                } else {
                    originalImage = Imaging.getBufferedImage(f);
                }

                if (originalImage == null) {
                    throw new IOException("Cannot load the original image !");
                }

                int originalWidth = originalImage.getWidth(null);
                int originalHeight = originalImage.getHeight(null);


                Image resizedImage = originalImage.getScaledInstance(originalWidth - 1, originalHeight - 1, Image.SCALE_SMOOTH);

                Image initialSizedImage = resizedImage.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH);

                BufferedImage sanitizedImage = new BufferedImage(initialSizedImage.getWidth(null), initialSizedImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
                Graphics bg = sanitizedImage.getGraphics();
                bg.drawImage(initialSizedImage, 0, 0, null);
                bg.dispose();
                try (OutputStream fos = Files.newOutputStream(f.toPath(), StandardOpenOption.WRITE)) {
                    if (!fallbackOnApacheCommonsImaging) {
                        ImageIO.write(sanitizedImage, formatName, fos);
                    } else {
                        ImageParser imageParser;
                        switch (formatName) {
                            case "TIFF": {
                                imageParser = new TiffImageParser();
                                break;
                            }
                            case "PCX": {
                                imageParser = new PcxImageParser();
                                break;
                            }
                            case "DCX": {
                                imageParser = new DcxImageParser();
                                break;
                            }
                            case "BMP": {
                                imageParser = new BmpImageParser();
                                break;
                            }
                            case "GIF": {
                                imageParser = new GifImageParser();
                                break;
                            }
                            case "PNG": {
                                imageParser = new PngImageParser();
                                break;
                            }
                            case "WBMP": {
                                imageParser = new WbmpImageParser();
                                break;
                            }
                            case "XBM": {
                                imageParser = new XbmImageParser();
                                break;
                            }
                            case "XPM": {
                                imageParser = new XpmImageParser();
                                break;
                            }
                            default: {
                                throw new IOException("Format of the original image is not supported for write operation !");
                            }

                        }
                        imageParser.writeImage(sanitizedImage, fos, new HashMap<>());
                    }

                }
                safeState = true;
            }

        } catch (Exception e) {
            safeState = false;
            LOG.warn("Error during Image file processing !", e);
        }
        return safeState;
    }

}