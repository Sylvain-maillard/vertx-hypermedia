package com.sylvain.vertx.hypermedia;

import io.vertx.ext.web.Router;

/**
 * Created by SylvainMaillard on 20/03/2016.
 */
public abstract class Resource {

    public void setupRoutes(Router router) {
        // analyse class and add route dynamically.
    }

}
