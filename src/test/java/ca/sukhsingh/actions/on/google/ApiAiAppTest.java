package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.BasicCard;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.RichResponse;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.SimpleResponse;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by sukhsingh on 2017-08-29.
 */
@RunWith(MockitoJUnitRunner.class)
public class ApiAiAppTest extends AssertHelper {

    @InjectMocks
    private ApiAiApp app;


    @Test
    public void appTellWithString()throws Exception {
        Response response = app.tell("hello");
        assertNotNull(response);
//        {
//            "speech": "hello",
//            "data": {
//                "google": {
//                    "expect_user_response": false,
//                    "is_ssml": false,
//                    "no_input_prompts": []
//                }
//            },
//            "contextOut": []
//        }
        assertSpeech(response, "hello");
        assertNotNull(response.getData());
        assertNotNull(response.getData().getGoogle());
        assertExpectUserResponseFalse(response);
        assertIsSsmlFalse(response);
        assertNotNull(response.getData().getGoogle().noInputPrompts);
        assertNotNull(response.getContextOut());

    }

    @Test
    public void appTellWithSSMLString()throws Exception {
        final String SPEECH = "<speak>hello</speak>";
        Response response = app.tell(SPEECH);
        assertNotNull(response);
        assertSpeech(response, SPEECH);
        assertIsSsmlTrue(response);
    }

    @Test
    public void appTellWithTextToSpeechAndDisplayText()throws Exception {
        Response response = app.tell("hello", "hi");

//        {
//            'speech': 'hello',
//            'data': {
//            'google': {
//                'expect_userresponse': false,
//                'rich_response': {
//                    'items': [
//                    {
//                        'simple_response': {
//                        'text_to_speech': 'hello',
//                                'display_text': 'hi'
//                    }
//                    }
//            ],
//                    'suggestions': []
//                }
//            }
//        },
//            'contextOut': []
//        }

        assertNotNull(response);
        assertSpeech(response, "hello");
        assertExpectUserResponseFalse(response);
        assertNotNullRichResponse(response);
        assertTextToSpeech(response, "hello");
        assertDisplayText(response, "hi");

    }

    @Test
    public void appTellWithTextToSpeechSSMLAndDisplayText()throws Exception {
        final String SPEECH = "<speak>Hello</speak>";
        final String DISPLAYTEXT = "Hi";
        Response response = app.tell(SPEECH, DISPLAYTEXT);

// {
//     "contextOut": [],
//     "data": {
//         "google": {
//             "expectUserResponse": false,
//                 "richResponse": {
//                 "items": [{
//                     "simpleResponse": {
//                         "ssml": "<speak>hello</speak>",
//                         "displayText": "simple"
//                     }
//                 }],
//                     "suggestions": []
//             }
//         }
//     }
// }
        assertNotNull(response);
        assertSsmlText(response, SPEECH);
        assertDisplayText(response, DISPLAYTEXT);
        assertExpectUserResponseFalse(response);
    }

    @Test
    public void appTellWithSimpleResponse()throws Exception {
        final String SPEECH = "Hello";
        final String DISPLAYTEXT = "Hi";
        SimpleResponse simpleResponse = new SimpleResponse(SPEECH, DISPLAYTEXT);
        Response response = app.tell(simpleResponse);
        assertNotNull(response);
        assertExpectUserResponseFalse(response);
        assertTextToSpeech(response,SPEECH);
        assertSsmlText(response,null);
        assertDisplayText(response,DISPLAYTEXT);
    }

    @Test
    public void appTellWithSimpleResponseWithSSML()throws Exception {
        final String SPEECH = "<speak>Hello</speak>";
        final String DISPLAYTEXT = "Hi";
        SimpleResponse simpleResponse = new SimpleResponse(SPEECH, DISPLAYTEXT);
        Response response = app.tell(simpleResponse);
        assertNotNull(response);
        assertExpectUserResponseFalse(response);
        assertTextToSpeech(response,null);
        assertSsmlText(response,SPEECH);
        assertDisplayText(response,DISPLAYTEXT);
    }

    @Test
    public void appTellWithRichResponse()throws Exception {
        Response response = app.tell(app.buildRichResponse()
                .addSimpleResponse("hello", "hi")
                .addSuggestions(new String [] {"say this", "say that"}));

//        {
//            "speech": "hello",
//            "data": {
//            "google": {
//                "expect_user_response": false,
//                "rich_response": {
//                    "items": [{
//                        "simple_response": {
//                        "text_to_speech": "hello",
//                        "display_text": "hi"
//                     }
//                  }],
//                  "suggestions": [{
//                      "title": "Say this"
//                  },
//                  {
//                      "title": "or this"
//                  }]
//                }
//            }
//          },
//            "contextOut": []
//        }



        assertNotNull(response);
        assertSpeech(response, "hello");
        assertExpectUserResponseFalse(response);
        assertNotNullRichResponse(response);
        assertTextToSpeech(response, "hello");
        assertDisplayText(response, "hi");
        assertSuggestions(response, "say this", "say that");

    }

    @Test
    public void appTellWithRichResponseWithSSML()throws Exception {

        //SimpleResponse with SSML

        final String SSML = "<speak>hello</speak>";

        Response response = app.tell(app.buildRichResponse()
                .addSimpleResponse(new SimpleResponse(SSML, "hi"))
                .addSuggestions(suggestions("say this", "say that")));

        assertNotNull(response);
        assertExpectUserResponseFalse(response);
        assertNotNullRichResponse(response);
        assertTextToSpeech(response, null);
        assertSsmlText(response, SSML);
        assertDisplayText(response, "hi");
        assertSuggestions(response, "say this", "say that");

    }

    @Test
    public void appTellWithOneNullParam()throws Exception {
        Response response = app.tell("");
        assertNull(response);
    }

    @Test
    public void appTellWithTwoNullParams()throws Exception {
        Response response = app.tell(null,null);
        assertNull(response);
    }

    @Test
    public void appTellWithStringAndNullParams()throws Exception {
        Response response = app.tell("hi", null);
        assertNull(response);
    }

    @Test
    public void appTellWithNullAndStringParams()throws Exception {
        Response response = app.tell(null, "hi");
        assertNull(response);
    }


    //********* APP ASK **********

    @Test
    public void appAskWithString()throws Exception {

        Response response;

        response = app.ask("hello");
        // {
        //   'speech': 'hello',
        //   'data': {
        //     'google': {
        //       'expect_user_response': true,
        //       'is_ssml': false,
        //       'no_input_prompts': []
        //     }
        //   },
        //   'contextOut': [
        //     {
        //       'name': '_actions_on_google_',
        //       'lifespan': 100,
        //       'parameters': {}
        //     }
        //   ]
        // }

        assertNotNull(response);
        assertSpeech(response, "hello");
        assertExpectUserResponseTrue(response);
        assertIsSsmlFalse(response);

    }

    @Test
    public void appAskWithStringWithSSML()throws Exception {

        Response response;

        response = app.ask("<speak>hello</speak>");
        assertNotNull(response);
        assertSpeech(response, "<speak>hello</speak>");
        assertExpectUserResponseTrue(response);
        assertIsSsmlTrue(response);

    }

    @Test
    public void appAskWithTextToSpeechAndNoInputPrompts() throws Exception {
        Response response = app.ask("Hello", new String[] {"Can you say that again ?", "What ?"});
        assertNotNull(response);
        assertSpeech(response,"Hello");
        assertEquals("Can you say that again ?", response.getData().getGoogle().getNoInputPrompts().get(0).getTextToSpeech());
        assertEquals("What ?", response.getData().getGoogle().getNoInputPrompts().get(1).getTextToSpeech());
    }

    @Test
    public void appAskWithTextToSpeechAndNoInputPromptsWithSSML() throws Exception {
        Response response = app.ask("<speak>Hello</speak>", new String[] {"Can you say that again ?", "What ?"});
        assertNotNull(response);
        assertSpeech(response,"<speak>Hello</speak>");
        assertIsSsmlTrue(response);
        assertEquals("Can you say that again ?", response.getData().getGoogle().getNoInputPrompts().get(0).getTextToSpeech());
        assertEquals("What ?", response.getData().getGoogle().getNoInputPrompts().get(1).getTextToSpeech());
    }

    @Test
    public void appAskWithTextToSpeechAndDisplayText()throws Exception {
        Response response = app.ask("hello", "hi");
        //  {
        //   'speech': 'hello',
        //   'data': {
        //     'google': {
        //       'expect_user_response': true,
        //       'rich_response': {
        //         'items': [
        //           {
        //             'simple_response': {
        //               'text_to_speech': 'hello',
        //               'display_text': 'hi'
        //             }
        //           }
        //         ],
        //         'suggestions': []
        //       }
        //     }
        //   },
        //   'contextOut': [
        //     {
        //       'name': '_actions_ongoogle_',
        //       'lifespan': 100,
        //       'parameters': {}
        //     }
        //   ]
        // };

        assertNotNull(response);
        assertSpeech(response,"hello");
        assertExpectUserResponseTrue(response);
        assertNotNullRichResponse(response);
        assertTextToSpeech(response, "hello");
        assertDisplayText(response, "hi");

    }

    @Test
    public void appAskWithTextToSpeechAndDisplayTextWithSSML()throws Exception {
        final String SSML = "<speak>Hello</speak>";
        Response response = app.ask(SSML, "hi");

        assertNotNull(response);
        assertExpectUserResponseTrue(response);
        assertNotNullRichResponse(response);
        assertSsmlText(response, SSML);
        assertTextToSpeech(response, null);
        assertDisplayText(response, "hi");
    }

    @Test
    public void appAskWithSimpleResponseWithSSML()throws Exception {
        final String SSML = "<speak>Hello</speak>";
        final String DISPLAYTEXT = "Hi";
        SimpleResponse simpleResponse = new SimpleResponse(SSML, DISPLAYTEXT);
        Response response = app.ask(simpleResponse);
        assertNotNull(response);
        assertTextToSpeech(response, null);
        assertSsmlText(response, SSML);
        assertDisplayText(response, DISPLAYTEXT);
        assertExpectUserResponseTrue(response);

    }

    @Test
    public void appAskWithSimpleResponse()throws Exception {
        final String SPEECH = "Hello";
        final String DISPLAYTEXT = "Hi";
        SimpleResponse simpleResponse = new SimpleResponse(SPEECH, DISPLAYTEXT);
        Response response = app.ask(simpleResponse);
        assertNotNull(response);
        assertTextToSpeech(response, SPEECH);
        assertDisplayText(response, DISPLAYTEXT);
        assertExpectUserResponseTrue(response);

    }

    @Test
    public void appAskWithRichResponse()throws Exception {

        Response response = app.ask(app.buildRichResponse()
                .addSimpleResponse(new SimpleResponse("hello","hi"))
                .addSuggestions(Arrays.asList("say this", "or this")));

        //  {
        //   'speech': 'hello',
        //   'data': {
        //     'google': {
        //       'expect_user_response': true,
        //       'rich_response': {
        //         'items': [
        //           {
        //             'simple_response': {
        //               'text_to_speech': 'hello',
        //               'display_text': 'hi'
        //             }
        //           }
        //         ],
        //         'suggestions': [
        //           {
        //             'title': 'Say this'
        //           },
        //           {
        //             'title': 'or this'
        //           }
        //         ]
        //       }
        //     }
        //   },
        //   'contextOut': [
        //     {
        //       'name': '_actions_on_google_',
        //       'lifespan': 100,
        //       'parameters': {}
        //     }
        //   ]
        // }

        assertNotNull(response);
        assertSpeech(response, "hello");
        assertExpectUserResponseTrue(response);
        assertNotNullRichResponse(response);
        assertTextToSpeech(response,"hello");
        assertDisplayText(response, "hi");
        assertSuggestions(response, "say this", "or this");

    }

    @Test
    public void appAskWithssml()throws Exception {
        final String SPEECH = "<speak>hello</speak>";
        Response response = app.ask(SPEECH);
        assertNotNull(response);
        assertIsSsmlTrue(response);
        assertExpectUserResponseTrue(response);
        assertSpeech(response, SPEECH);
    }

    @Test
    public void appAskWithOneNullParam()throws Exception {
        Response response = app.ask("");
        assertNull(response);
    }

    @Test
    public void appAskWithTwoNullParams()throws Exception {
        Response response = app.ask("", "");
        assertNull(response);
    }

    // ****** ASK WITH LIST *******

    /*
    askWithList(inputPrompt, list)
        1. null inputPrompt
        2. null list
        3. list < 2
        4. InputPrompt
            a. as string
                i.  string as textToSpeech
                ii. String as SSML
            b. as SimpleResponse
            c. as RichResponse
     */

    @Test
    public void askWithListNullInputPrompt() throws Exception {
        Response response = app.askWithList(null, new List());
        assertNull(response);
    }

    @Test
    public void askWithListNullList() throws Exception {
        Response response = app.askWithList(new Object(), null);
        assertNull(response);
    }

    @Test
    public void askWithListWithListSizeInvalid() throws Exception {}

    @Test
    public void askWithListWhereInputPromptParamIsString() throws Exception {
    }

    @Test
    public void askWithListWhereInputPromptParamIsStringAsSSML() throws Exception {}
    @Test
    public void askWithListWhereInputPromptParamIsSimpleResObj() throws Exception {}
    @Test
    public void askWithListWhereInputPromptParamIsRichResObj() throws Exception {}

    // ****** ASK WITH CAROUSEL *******
    /*
    askWithCarousel(inputPrompt, carousel)
        1. null inputPrompt
        2. null list
        3. list < 2
        4. InputPrompt
            a. as string
                i.  string as textToSpeech
                ii. String as SSML
            b. as SimpleResponse
            c. as RichResponse
     */

    @Test
    public void askWithCarouselWithNullInputPrompt() throws Exception {}
    @Test
    public void askWithCarouselWithNullList() throws Exception {}
    @Test
    public void askWithCarouselWithInvalidListSize() throws Exception {}
    @Test
    public void askWithCarouselWhereInputPromptisString() throws Exception {}
    @Test
    public void askWithCarouselWhereInputPromptParamIsStringAsSSML() throws Exception {}
    @Test
    public void askWithCarouselWhereInputPromptParamIsSimpleResObj() throws Exception {}
    @Test
    public void askWithCarouselWhereInputPromptParamIsRichResObj() throws Exception {}

    // ******** AssistantApp **********

    /*
    buildRichResponse
     */
    @Test
    public void buildRichResponseTest() throws Exception {
        assertNotNull(app.buildRichResponse());
    }

    /*
    buildRichResponse(richResponse)
     */
    @Test
    public void buildRichResponseTestWithRichResAsParam() throws Exception {
        RichResponse richResponse = new RichResponse();
        richResponse.addSimpleResponse("Hello");
        RichResponse response = app.buildRichResponse(richResponse);
        assertNotNull(response);
        assertEquals("Hello", response.getItems().get(0).getSimpleResponse().getTextToSpeech());
    }

    /*
    buildBasicCard(string)
     */
    @Test
    public void buildBasicCardTest() throws Exception {
        BasicCard basicCard = app.buildBasicCard("Hello");
        assertNotNull(basicCard);
    }

    /*
    buildList(string)
     */
    @Test
    public void buildListTest() throws Exception {
    }

    /*
    buildCarousel()
     */

    @Test
    public void buildCarousel() throws Exception {

    }
    /*
    buildOptionItem()
     */

    @Test
    public void buildOptionItem() throws Exception {
    }

    /*
    askForPermissions(string, List<String>
        1. Null context
        2. Null list
        3. Empty List
        4. Invalid Permission Type
        5. HappyPath
     */

    @Test
    public void askForPermissionsWithNullContext() throws Exception {
    }

    @Test
    public void askForPermissionsWithNullListParam() throws Exception {
    }

    @Test
    public void askForPermissionsWithEmptyListParam() throws Exception {
    }

    @Test
    public void askForPermissionsWithInvalidPermissionType() throws Exception {
    }

    @Test
    public void askForPermissionsHappyPath() throws Exception {

    }

    /*
    askForPermission(string, string)
        1. Null context
        2. Null permission
        3. Invalid Permission
        4. HappyPath
     */
    @Test
    public void askForPermissionWithNullContext() throws Exception {
    }

    @Test
    public void askForPermissionWithNullPermission() throws Exception {
    }
    @Test
    public void askForPermissionWithInvalidPermissionType() throws Exception {
    }

    @Test
    public void askForPermissionHappyPath() throws Exception {

    }
}
