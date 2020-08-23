# Install nodejs
# Install npm
# Install sequelize-cli
> npm install -g sequelize-cli

 v6.2.0 installed


# Install apollo server and graphql
* Ref: <https://github.com/apollographql/apollo-server#installation-standalone>
> npm install apollo-server graphql

## Add basic skeleton at the root of project
```
const { ApolloServer, gql } = require('apollo-server');

// The GraphQL schema
const typeDefs = gql`
  type Query {
    "A simple type for getting started!"
    hello: String
  }
`;

// A map of functions which return data for the schema.
const resolvers = {
  Query: {
    hello: () => 'world',
  },
};

const server = new ApolloServer({
  typeDefs,
  resolvers,
});

server.listen().then(({ url }) => {
  console.log(`🚀 Server ready at ${url}`);
});
```