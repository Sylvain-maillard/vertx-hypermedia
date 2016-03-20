package com.sylvain.vertx.hypermedia;

import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.Router;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by SylvainMaillard on 20/03/2016.
 */
@RunWith(VertxUnitRunner.class)
public class ResourceTest {

    @Rule
    public RunTestOnContext rule = new RunTestOnContext();


    @HStateRepresentation(ofResource ="test")
    public static class TestRepresentation {

    }

    @HResource(name="test")
    public static class TestResource extends Resource {
    }


    @Test
    public void setupRoutes(TestContext context) throws Exception {

        Router router = Router.router(rule.vertx());

        Resource resource = new TestResource();

        resource.setupRoutes(router);

        context.assertFalse(router.getRoutes().isEmpty());
        // I should have the following routes:
        context.assertTrue(router.getRoutes().stream().filter(r -> r.getPath().equals("/tests")).findFirst().isPresent());
        context.assertTrue(router.getRoutes().stream().filter(r -> r.getPath().equals("/tests/:id")).findFirst().isPresent());
        context.assertTrue(router.getRoutes().stream().filter(r -> r.getPath().equals("/tests/:id/:action")).findFirst().isPresent());
    }
}