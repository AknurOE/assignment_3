package mst;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.util.List;


public class IOHandler {


    public static class GraphList {
        public List<Graph> graphs;
    }


    public static List<Graph> readGraphsFromJson(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            GraphList wrapper = mapper.readValue(new File(filePath), GraphList.class);
            return wrapper.graphs;
        } catch (Exception e) {
            System.out.println("IOHandler.readGraphsFromJson error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    public static void writeResultsToJson(String filePath, List<Result> results) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(filePath), results);
            System.out.println(" Results written to " + filePath);
        } catch (Exception e) {
            System.out.println("IOHandler.writeResultsToJson error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}