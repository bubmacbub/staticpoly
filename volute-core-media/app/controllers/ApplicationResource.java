package controllers;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.volute.util.VoluteCacheClient;

import models.Application;
import play.mvc.Controller;
import play.cache.Cache;
//import java.util.*;
import play.data.Form;
import play.mvc.*;
import static play.libs.Json.toJson;

public class ApplicationResource extends Controller {

    //public static Map<String, models.Application> applications = new HashMap<String, models.Application>();
    private  VoluteCacheClient cc=VoluteCacheClient.getInstance();
    @BodyParser.Of(BodyParser.Json.class)
    public  Result create() {
        try {
            Form<models.Application> form = Form.form(models.Application.class).bindFromRequest();
            Map<String, models.Application> applications;

            if (form.hasErrors()) {
                return badRequest(form.errorsAsJson());
            } else {
                models.Application app = form.get();
                app.setAppInfo(UUID.randomUUID().toString());
                VoluteCacheClient cc=VoluteCacheClient.getInstance();
                applications=(Map<String, Application> )cc.getValue("APPS");
                if(applications == null)
                {
                	applications= new HashMap<String, models.Application>();
                	
                }
                applications.put(app.getName(), app);
               
                
                cc.setValue("APPS", applications);
                return created(toJson(app));
            }

        } catch (Exception e) {
            return internalServerError(e.getMessage());
        }
    }
    
    public  Result index() {
    	VoluteCacheClient cc=VoluteCacheClient.getInstance();
    	Map<String, models.Application> apps=(Map<String, Application>)cc.getValue("APPS");
        return ok(toJson(apps));
    }  
    
}
