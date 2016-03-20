package com.sylvain.vertx.hypermedia;

import io.vertx.core.Handler;

import java.util.List;

/**
 * Created by SylvainMaillard on 20/03/2016.
 */
public interface ResourceStore {

    void selectAll(Handler<List<ResourceStateRepresentation>> afterSelection);
    void selectById(String id, Handler<ResourceStateRepresentation> currentResourceRepresentation);
    void saveResourceState(String id, ResourceStateRepresentation newResourceRepresentation, Handler<Void> onceUpdated);
}
