# Vert.x Hypermedia framework

## Concepts

Hypermedia is related to Restful architectural style. There are no out-of-the box support for this style in vert.x echosystem.

The idea of hypermedia is to translate hypertext in html to the api/rest/application design world: you will navigate through applications
 using a set of links. Each links that you invoke using plain HTTP verbs (POST,GET, etc) will be __transitions__ between __states__ of particular
 __resources__ of your application. See also HATEOAS.

## Resources and representations

Resources are endpoints to your application. A resource has a name, may have a parent which is a resource too (in case of subresource), and has at last one representation.
If they are more than one representation for your resource, theses representations can be seen as states.

### Exemple

Say you have a system that perform payments. You will have a resource named "payments" (use plural as it seems to be the standard...). 
To create a payment, you will do:
 
    POST /payments/
    
Note that the post will return you the location of the resource, you will have to do a GET to actually read the state of the new payment:

    HTTP 201
    location: /payments/10
    
When you GET this location, the current representation/state of the resource is returned, in Json format:

    GET /payments/10
    HTTP 200
    {
        links:[{"rel":"@self","url":"http://<host:port>/payments/10"}]
    }

Every resource representation should provide at least one link to self. We'll see later how this could be used in hypermedia.

### Allowed endpoints

With the hypermedia framework, the following behavior is permitted, given a resource named *Resource*

| Verb | Url | Effect | Response from app |
|------|-----|--------|-------------------|
| POST | /Resource | Create a new Resource with an Id provided by the application | HTTP 201 + location header | 
| GET  | /Resource | Will browse all instances of the Resource. In real life production system, this will be used to search, filtering, paging | HTTP 200 + list of links to resources 
| GET  | /Resource/:id | Retrieve the current representation of the resource. You may implements here advanced scenario for field selections, expend elements, etc. | HTTP 200 + json body content
| DELETE | /Resource | Delete the whole set of resource, or a subset given a query (in query params). | HTTP Accepted code ?
| DELETE | /Resource/:id | Delete this resources. | HTTP Accepted ?
| PUT    | /Resource/:id | update the current representation, this operation is idempotent: it should not change the state of the resource. Not easy to implements, beware with this one. :boom: |
| POST   | /Resource/:id/:event | send an event to the resource, telling it to change state. | HTTP Accepted ?

## Identifiable Resources and Links

### Using X-forwarded-host and X-forwarded-port headers.

## Subresources

## Actions / event ?

To mutate a resource state to another state, you can use an action / send an event. See this as a state machine transition.

