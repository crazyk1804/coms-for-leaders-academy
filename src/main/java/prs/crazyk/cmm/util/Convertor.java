package prs.crazyk.cmm.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
*
* @Class Name : Convertor.java
* @Description : 모델 객체에 대한 컨버팅 툴 클래스. 마샬링, 시리얼라이징, 기타 등등 변환 관련된 로직은 이 클래스에 작성한다.
* @Modification Information
* @
* @     수정일     	  수정자                 수정내용
* @  ----------   	--------    ---------------------------
* @  2015.12.21     김동한       최초 생성
*
* @author 김동한
* @since 2015.12.21
* @version 1.0
* @see
*/
public class Convertor {
//	static class PascalNamingStrategy extends LowerCaseWithUnderscoresStrategy {
//	    @Override
//	    public String translate(String property) {
//	    	if(property.length()<1) return property.toUpperCase();
//	        return Character.toUpperCase(property.charAt(0)) + property.substring(1);
//	    }
//	}
	
	private static ObjectMapper objMapper = null;
//	private static ObjectMapper pascalObjMapper = null;
	
	static {
		objMapper = new ObjectMapper();
//		objMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);	// Jackson 1.9 설정
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss+09:00"));
//		pascalObjMapper = new ObjectMapper();
//		pascalObjMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//		pascalObjMapper.setPropertyNamingStrategy(new PascalNamingStrategy());
	}
	
	/**
	 * json 문자열을 객체로 변환해준다
	 * @param jsonString json 문자열
	 * @param cls 반환 받을 타입
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T jsonToObject(String jsonString, Class<T> cls) throws Exception {
		T result = null;
		try {
			result = objMapper.readValue(jsonString, cls);
		} catch(JsonParseException jpe) {
			throw new Exception(jpe);
		} catch(JsonMappingException jme) {
			throw new Exception(jme);
		} catch(IOException ioe) {
			throw new Exception(ioe);
		}
		
		return result;
	}

	public static <T> T jsonToObject(String jsonString) throws Exception {
		T result = null;
		try {
			result = objMapper.readValue(jsonString, new TypeReference<T>() {});
		} catch(JsonParseException jpe) {
			throw new Exception(jpe);
		} catch(JsonMappingException jme) {
			throw new Exception(jme);
		} catch(IOException ioe) {
			throw new Exception(ioe);
		}

		return result;

	}

	public static <T> T jsonToObject(String jsonString, Class<T> cls, Class<?>...classes) throws JsonProcessingException {
		T result =  null;
		JavaType type = objMapper.getTypeFactory().constructParametricType(cls, classes);
		result = objMapper.readValue(jsonString, type);
		return result;
	}
	
	/**
	 * 본 시스템에서의 환경설정에 따라 dto로 받은 json문자열을 객체로 변환해준다.
	 * @param dto key와 json 문자열로 구성된 map 객체
	 * @param key 변환 대상 json 문자열의 key값
	 * @param cls 반환 받을 타입
	 * @return
	 * @throws Exception 
	 */
	public static <T> T jsonToObject(Map<String, Object> dto, String key, Class<T> cls) throws Exception {
		String jsonString = (String)dto.get(key);
		return jsonToObject(jsonString, cls);
	}
	
	/**
	 * 리스트가 json으로 표현된 경우 리스트의 객체로 변환해준다.
	 * @param dto key와 json 문자열로 구성된 map 객체
	 * @param key 변환 대상 json 문자열의 key값
	 * @param cls 반환 받을 타입
	 */
	public static <T> List<T> jsonToList(Map<String, Object> dto, String key, Class<T> cls) throws Exception {
		String jsonString = (String)dto.get(key);
		return jsonToList(jsonString, cls); 
	}
	
	/**
	 * 리스트가 json으로 표현된 경우 리스트의 객체로 변환해준다.
	 * @param jsonString
	 * @param cls
	 */
	public static <T> List<T> jsonToList(String jsonString, Class<T> cls) throws Exception {
		List<T> result = null;
		
		try {
			JavaType type = objMapper.getTypeFactory().constructParametricType(List.class, cls);
			result = objMapper.readValue(jsonString, type);
		} catch(JsonParseException jpe) {
			throw new Exception(jpe);
		} catch(JsonMappingException jme) {
			throw new Exception(jme);
		} catch(IOException ioe) {
			throw new Exception(ioe);
		}
		
		return result; 
	}
	
	/**
	 * 객체를 json 문자열로 변환해준다
	 * @param obj 변환 대상 객체
	 * @return
	 * @throws Exception 
	 */
	public static String objectToJson(Object obj) throws Exception {
		String result = null;
		try {
			result = objMapper.writeValueAsString(obj);
		} catch(JsonParseException jpe) {
			throw new Exception(jpe);
		} catch(JsonMappingException jme) {
			throw new Exception(jme);
		} catch(IOException ioe) {
			throw new Exception(ioe);
		}
		
		return result; 
	}
	
	/**
	 * 객체를 XML 문자열로 변환한다.
	 * @param obj
	 * @param classes
	 * @return
	 * @throws JAXBException
	 */
	public static String objectToXmlString(Object obj, Class<?>...classes) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(classes);
	    Marshaller m = context.createMarshaller();
	    m.setProperty(Marshaller.JAXB_ENCODING, "EUC-KR");
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); 
	    m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
	    
	    StringWriter writer = new StringWriter();
	    writer.append("<?xml version=\"1.0\" encoding=\"EUC-KR\"?>\r\n");
	    writer.append("<!DOCTYPE EXCHANGE SYSTEM \"exchange_mis.dtd\">\r\n");
	    m.marshal(obj, writer);
	    return writer.toString();
	}
	
	/**
	 * XML문자열을 객체로 변환한다.
	 * @param xmlString
	 * @param cls
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlStringToObject(String xmlString, Class<T> cls) throws JAXBException {
		xmlString = xmlString.replaceAll("&", "&amp;");
		JAXBContext context = JAXBContext.newInstance(cls);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		StringReader sReader = new StringReader(xmlString);
		T t = (T)unmarshaller.unmarshal(sReader);
		
		return t;
	}

	/**
	 * double 형 숫자를 특정 소숫점에 반올림한 문자열로 반환
	 * @param value
	 * @param roundPos
	 * @return
	 */
	public static String doubleToRoundString(double value, int roundPos) {
		String pattern = "0.00";
		DecimalFormat dformat = new DecimalFormat(pattern);
		return dformat.format(value);
	}

	public static <T> List<T> fromArray(T...t) {
		return new ArrayList<>(Arrays.asList(t));
	}
}
