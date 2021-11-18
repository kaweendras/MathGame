/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.net.URL;

/**
 *
 * @author Kaweesha
 */
public class google_image_charts {
    public String getApi(String d1,String d2,String d3,String d4,String v1,String v2,String v3,String v4){
        String api = "https://chart.googleapis.com/chart?chs=500x200&chd=t:"+d1+","+d2+","+d3+","+d4+"&cht=p3&chl="+d1+"|"+d2+"|"+d3+"|"+d4+"";
        return api;
    }

   
    
}
