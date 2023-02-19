exports.handler = async (event, context) => {
  const headers = {
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Headers": "Content-Type"
  };

  // Add headers to the response
  const response = {
    headers: headers,
    statusCode: 200,
    body: ""
  };

  const jsonServer = require('json-server');
  const server = jsonServer.create();
  const router = jsonServer.router('../db.json');
  const middlewares = jsonServer.defaults();

  server.use(middlewares);
  server.use(router);
  server.listen(3000, () => {
    console.log('Server is running');
  });
}

