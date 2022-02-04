# JSON Column Mapping with JPA

This repository shows how to build mapping between MySQL JSON column to JPA field.

## Context

We have to write a code for a cart aggregate, and it has cart and cart items. Those are 1:N relationship. Cart items could be value object or entity, but just assume that it is a value object based on the business context.

For 1:N value objects, `@ElementCollection` usually is used because it provides all basic characteristic of value objects. However, this leads to always delete all and insert all on cart item tables. This is unavoidable.

One idea as a workaround is using JSON column for cart items. By doing that, we only manage a single table and items column contains multiple data represented by JSON.

Thus, we have to map between two classes into one table including JSON.

## Implementation

We might implement the mapping with Hibernate's UserType, but smart guys already made it - [hibernate-types](https://github.com/vladmihalcea/hibernate-types).

## Reference

- https://github.com/vladmihalcea/hibernate-types
- https://vladmihalcea.com/how-to-map-json-objects-using-generic-hibernate-types/
