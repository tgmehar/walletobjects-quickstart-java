wallet-objects-quickstart-java
==============================

This sample aims to provide a straight forward example of how to integrate the basic components of the Wallet Objects API.  To run the quickstart review the quickstart guide [here](https://developers.google.com/commerce/wallet/objects/quickstart-java).

The app showcases several aspects of the API
* Creation of Wallet Classes and Objects
* Save to Wallet insertion of Classes and Objects
* Webservice API

##Creation of Wallet Classes and Objects
Example code for creation of Classes and Objects can be found under the src/com/google/wallet/objects/verticals directory.  Each vertical is broken out into it's own class.  Classes are inserted via the WobInsertServlet or in the case of Boarding Passes via Save to Wallet.

## Save to Wallet insertion of Classes and Objects
Save to Wallet is handled on both the client and server side.  war/index.html is the landing page which then includes app.js.  App.js makes a request to the WobGenerateJwtServlet to generate vertical specific JWTs.  If you wish to run this on a domain other than localhost, you must update the origins List in WobGenerateJwtServlet.  Once all of the JWTs are generated, app.js inserts the appropriate g:wallet tags and the Save to Wallet JS.  The JS must be appended after the g:wallet tags because it parses the page to render Save to Wallet buttons when it's completed loading.

## Webservice API
The Webservice API handler is WobWebserviceHandlerServlet.  The URL to this handler is defined within war/web.xml.  This servlet handles Webservice requests, generates Loyalty Objects converts them to JWTs and responds with the JWT.  You can configure your discoverable to point to the URL handled by the WobWebserviceHandlerServlet.
