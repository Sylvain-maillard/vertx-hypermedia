package com.sylvain.vertx.hypermedia;

import io.vertx.core.AsyncResultHandler;
import io.vertx.core.Handler;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.RunTestOnContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import io.vertx.ext.web.Router;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SylvainMaillard on 20/03/2016.
 */
@RunWith(VertxUnitRunner.class)
public class ResourceTest {

    @Rule
    public RunTestOnContext rule = new RunTestOnContext();

    @HResourceStore(ofResource="tests")
    public static class TestStore implements ResourceStore {
        private Map<String, ResourceStateRepresentation> store = new HashMap<>();

        @Override
        public void selectAll(Handler<List<ResourceStateRepresentation>> afterSelection) {
            afterSelection.handle(new ArrayList<>(store.values()));
        }

        @Override
        public void selectById(String id, Handler<ResourceStateRepresentation> currentResourceRepresentation) {
            currentResourceRepresentation.handle(store.get(id));
        }

        @Override
        public void saveResourceState(String id, ResourceStateRepresentation newResourceRepresentation, Handler<Void> onceUpdated) {
            store.put(id, newResourceRepresentation);
            onceUpdated.handle(null);
        }
    }

    @HStateRepresentation(ofResource ="tests")
    public static class TestRepresentation extends ResourceStateRepresentation {

    }

    @HResource(name="tests")
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