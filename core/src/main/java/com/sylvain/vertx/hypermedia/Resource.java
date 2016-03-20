package com.sylvain.vertx.hypermedia;

import io.vertx.ext.web.Router;

/**
 * Created by SylvainMaillard on 20/03/2016.
 */
public abstract class Resource {

    public abstract void setupRoutes(Router router);

}
