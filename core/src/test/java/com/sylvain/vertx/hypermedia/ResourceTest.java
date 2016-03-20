package com.sylvain.vertx.hypermedia;

import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Created by SylvainMaillard on 20/03/2016.
 */
@RunWith(VertxUnitRunner.class)
public class ResourceTest {

    @Rule
    public RunTestOnContext rule = new RunTestOnContext();

    @Test
    public void setupRoutes(TestContext context) throws Exception {

        Router router = Router.router(rule.vertx());

        Resource resource = new Resource() {
            @Override
            public void setupRoutes(Router router) {
                router.get("/xxxx").handler(this::getHandler);
            }

            private void getHandler(RoutingContext routingContext) {

            }
        };

        resource.setupRoutes(router);

        context.assertFalse(router.getRoutes().isEmpty());
    }
}