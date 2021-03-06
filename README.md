# Actions on google Client library for Java & Spring
Unofficial Actions on Google SDK for Java & spring

[![Build Status](https://travis-ci.org/sukhvinder1/actions-on-google-java.svg?branch=master)](https://travis-ci.org/sukhvinder1/actions-on-google-java)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3ed58fe1ebdc4af19acd3bdb19b39c52)](https://www.codacy.com/app/sukhvinder1/actions-on-google-java?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=sukhvinder1/actions-on-google-java&amp;utm_campaign=Badge_Grade)
[![Maven Central](https://img.shields.io/badge/maven--central-1.0.2-brightgreen.svg)](http://search.maven.org/#artifactdetails%7Cca.sukhsingh.actions%7Cactions-on-google%7C1.0.2%7Cjar)
[![Javadocs](http://javadoc.io/badge/ca.sukhsingh.actions/actions-on-google.svg)](http://javadoc.io/doc/ca.sukhsingh.actions/actions-on-google/1.0.1)

This is a port of the [official Node.js SDK](https://github.com/actions-on-google/actions-on-google-nodejs) to Java or Spring.

## Setup Instructions 
Library is available on maven central.


For maven simply add below maven dependency :

__Maven:__

    <dependency>
      <groupId>ca.sukhsingh.actions</groupId>
      <artifactId>actions-on-google</artifactId>
      <version>1.0.1</version>
    </dependency>


If you are using gradle, simply add the dependency as follows:
__Gradle:__
    
    compile 'ca.sukhsingh.actions:actions-on-google:1.0.1'

## To Get Started
If you are using spring-mvc/Spring-boot:
Just Inject ApiAiApp in the class. 

* ApiAiApp : This class will give access to all the methods which can be used to prepare a response for user
* Request : Request is object is upto date request which api.ai will send to the webhook
* Response : This object is upto date response which api.ai will consume

##### Example :

```java
@RestController
public class AppAsk {
    @Inject
    ApiAiApp app;

    @PostMapping(value = "/webHook")
    public ResponseEntity<Response> tell(@RequestBody Request request) {
        String action = request.getAction();
        Response response = new Response();
        switch (action) {
            case ("input.ask") :
                response = app.ask("Hello World!");
                break;
            case ("input.ask.rich") :
                response = app.ask(
                        app.buildRichResponse()
                        .addSimpleResponse("Hello World!", "Hello World!")
                        .addSimpleResponse("Simple response with bubble")
                        .addSuggestions("Suggestion chip")
                        .addSuggestions("List", "Carousel")
                        .addSuggestionLink("Visit me", "http://example.com")
                );
                break;
        }
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
```

### Things you can do with this Library
* [Tell](https://github.com/sukhvinder1/actions-on-google-java/wiki/app.tell)
* [Ask](https://github.com/sukhvinder1/actions-on-google-java/wiki/app.ask)
* [Ask with List](https://github.com/sukhvinder1/actions-on-google-java/wiki/app.askWithList)
* [Ask with Carousel](https://github.com/sukhvinder1/actions-on-google-java/wiki/app.askWithCarousel)
* [Ask for Permission(s)](https://github.com/sukhvinder1/actions-on-google-java/wiki/app.askForPermisssion(s))
* Ask with no inputPrompts
* [Rich Response - Simple Response & Bubbles](https://github.com/sukhvinder1/actions-on-google-java/wiki/RichResponse---buildSimpleResponse)
* [Rich Response - Add suggestions & Link out to suggestion](https://github.com/sukhvinder1/actions-on-google-java/wiki/RichResponse---addSuggestions)
* [Rich Response - Basic Card](https://github.com/sukhvinder1/actions-on-google-java/wiki/RichResponse---buildBasicCard)

### License
See [License](https://github.com/sukhvinder1/actions-on-google-java/blob/master/LICENSE)
