# Actions on google Client library for Java & Spring
Unofficial Actions on Google SDK for Java & spring

[![Build Status](https://travis-ci.org/sukhvinder1/actions-on-google-java.svg?branch=master)](https://travis-ci.org/sukhvinder1/actions-on-google-java)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3ed58fe1ebdc4af19acd3bdb19b39c52)](https://www.codacy.com/app/sukhvinder1/actions-on-google-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sukhvinder1/actions-on-google-java&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://img.shields.io/badge/maven--central-1.0-brightgreen.svg)](http://search.maven.org/#artifactdetails%7Cca.sukhsingh.actions%7Cactions-on-google%7C1.0%7Cjar)
[![Javadocs](http://javadoc.io/badge/ca.sukhsingh.actions/actions-on-google.svg)](https://actions.sukhsingh.ca/docs/index.html)

This is a port of the [official Node.js SDK](https://github.com/actions-on-google/actions-on-google-nodejs) to Java or Spring.

## Setup Instructions 
Library is available on maven central.


For maven simply add below maven dependency :

__Maven:__

    <dependency>
      <groupId>ca.sukhsingh.actions</groupId>
      <artifactId>actions-on-google</artifactId>
      <version>1.0</version>
    </dependency>


If you are using gradle, simply add the dependency as follows:
__Gradle:__
    
    compile 'ca.sukhsingh.actions:actions-on-google:1.0'

## Spring boot example
    
    import ca.sukhsingh.actions.on.google.ApiAiApp;
    import ca.sukhsingh.actions.on.google.request.Request;
    import ca.sukhsingh.actions.on.google.response.Response;
    import ca.sukhsingh.actions.on.google.response.data.google.richresponse.SimpleResponse;
    
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    
    @RestController
    @RequestMapping(value = "/json")
    public class Controller {
        @Inject
        ApiAiApp app;
        
        @PostMapping(value = "/hook")
        public ResponseEntity<Response> tell(@RequestBody Request request) {
            rs = app.ask(app.buildRichResponse()
                            .addSimpleResponse(new SimpleResponse("Hello World!", null, "Hello World!"))
                            .addSuggestions("Basic Card", "Suggestion", "etc");
                            
            return new ResponseEntity<>(rs, HttpStatus.OK);
        }
    }
