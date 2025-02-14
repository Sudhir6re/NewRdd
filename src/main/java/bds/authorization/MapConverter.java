package bds.authorization;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;


public class MapConverter implements Converter {
	public boolean canConvert(Class type) {             
		return AbstractMap.class.isAssignableFrom(type);         
	}          

	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext context) {
		Map<String, Object> map = (Map<String, Object>) value;
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getValue() instanceof Map) {
				writer.startNode(entry.getKey());
				context.convertAnother(entry.getValue());
				writer.endNode();
			}
			else if (entry.getValue() instanceof Map[][]) {
				Map temp[][] = (Map[][]) entry.getValue();
				writer.startNode(entry.getKey());
				for(Map[] rowTemp : temp)
					for(Map colTemp : rowTemp)
					{
						if(colTemp.isEmpty())
							continue;
						writer.startNode("BifurcatedGISClassMap");
						context.convertAnother(colTemp);
						writer.endNode();
					}
				writer.endNode();
				temp = null;
			}
			else if (entry.getValue() instanceof List) {
				List list = (List)entry.getValue();
				writer.startNode(entry.getKey());
				for(Object obj : list)
				{
					context.convertAnother(obj);
				}
				writer.endNode();
			}
			else {
				writer.startNode(entry.getKey());
				writer.setValue(entry.getValue().toString());
				writer.endNode();
			}
		}

	}          
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {             
		Map<String, Object> map = new HashMap<String, Object>();      
		while(reader.hasMoreChildren()) {         
			reader.moveDown();        
			if(reader.getNodeName().endsWith("InnerMap")){
				Map innerMap = (java.util.Map)context.convertAnother(map, java.util.Map.class);
				map.put(reader.getNodeName(), innerMap);  
			}
			if(reader.getNodeName().endsWith("A")){
				Map innerMap = (java.util.Map)context.convertAnother(map, java.util.Map.class);
				map.put(reader.getNodeName(), innerMap);  
			}
			else if(reader.getNodeName().endsWith("InnerList")){
				List innerList = (java.util.List)context.convertAnother(map, java.util.List.class);
				map.put(reader.getNodeName(), innerList);  
			}else{
				map.put(reader.getNodeName(), reader.getValue());         
			}
			reader.moveUp();     
		}     
		return map; 
	} 
}
