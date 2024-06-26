package com.example.capacitortest.plugins;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.logging.Logger;

@CapacitorPlugin(name = "categoryPlugin")
public class CreateCategoryPlugin extends Plugin {

  private final Logger log = Logger.getLogger(this.getClass().getName());

  @PluginMethod()
  public void createCategory(PluginCall call) {
    log.warning("toto");
    log.warning(String.valueOf(call));
  }

  @PluginMethod()
  public void echo(PluginCall call) {

    var tt = call.getString("test");
    System.out.println(tt);

    JSObject json = new JSObject();
    json.put("return", tt);
    call.resolve(json);
  }

}
