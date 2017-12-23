package com.henu.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.BiConsumer;

public class PageData extends HashMap implements Map {
    private static Logger logger = LoggerFactory.getLogger(PageData.class);

    private Map<Object,Object> map ;
    private HttpServletRequest request;

    public static void main(String[] args) {
        PageData a = new PageData();
        a.put("one","1");
        System.out.println(a);
        PageData b = new PageData();
        b.put("two","2");
        b.putAll(a);
        System.out.println(b);
        List list = new ArrayList();
        list.add("222");
        list.add("22222");
        b.put("字符串组",list);
        System.out.println(b);
    }

    public PageData(){
        map = new HashMap<>();
    }

    public PageData(HttpServletRequest request){
        this.request = request;
        Map<Object,Object> resultMap = new HashMap<>();
        Map properties = request.getParameterMap();
        Map.Entry entry;
        String key = "";
        String value = "";
        for (Iterator iterator = properties.entrySet().iterator();iterator.hasNext();){
            entry = (Entry) iterator.next();
            key = (String) entry.getKey();
            Object values = entry.getValue();
            if (values == null){
                value = "";
            } else if (values instanceof String[]){
                value = "";
                String[] arr = (String[]) values;
                for (int i=0;i<arr.length;i++){
                    value += arr[i] + ",";
                }
                value = value.substring(0,value.length()-1);
            } else {
                value = values.toString();
            }
            resultMap.put(key,value);
        }
        map = resultMap;
        logger.error("pageData数据:{}",map.toString());
    }

    @Override
    public Object get(Object key) {
        Object value = null;
        if (map.get(key) instanceof Object[]){
            Object[] values = (Object[]) map.get(key);
            value = request == null ? values : (request.getParameter((String) key) == null ? values : values[0]);
        } else {
            value = map.get(key);
        }
        return value;
    }

    public String getString(Object key){
        String value = (String) get(key);
        return value == null ? "" : value;
    }

    public int getInt(Object key){
        Object value = get(key);
        return value == null ? null: Integer.parseInt(value.toString().trim());
    }

    public String getNullString(Object key){
        String value = (String) get(key);
        return value == null ? null : value;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return map.put(key, value);
    }

    @Override
    public void putAll(Map m) {
        map.putAll(m);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Set<Object> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }
    @Override
    public Set entrySet() {
        return map.entrySet();
    }
    @Override
    public boolean remove(Object key, Object value) {
        return super.remove(key, value);
    }

    @Override
    public void forEach(BiConsumer action) {
        map.forEach(action);
    }
}
