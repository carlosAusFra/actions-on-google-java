package ca.sukhsingh.actions.on.google;

import ca.sukhsingh.actions.on.google.response.Response;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.BasicCard;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.RichResponse;
import ca.sukhsingh.actions.on.google.response.data.google.richresponse.SimpleResponse;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.Carousel;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.Item;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.ListSelect;
import ca.sukhsingh.actions.on.google.response.data.google.systemintent.SystemIntentData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by sukhsingh on 2017-08-29.
 */
@RunWith(MockitoJUnitRunner.class)
public class DialogflowAppTest extends AssertHelper {

    @InjectMocks
    private DialogflowApp app;


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
    public void appTellWithEmptyParam()throws Exception {
        Response response = app.tell("");
        assertNull(response);
    }

    @Test
    public void appTellWithOneNullStringParam()throws Exception {
        String string = null;
        Response response = app.tell(string);
        assertNull(response);
    }

    @Test
    public void appTellWithOneNullSimpleResParam()throws Exception {
        SimpleResponse simpleResponse = null;
        Response response = app.tell(simpleResponse);
        assertNull(response);
    }

    @Test
    public void appTellWithOneNullRichResParam()throws Exception {
        RichResponse richResponse = null;
        Response response = app.tell(richResponse);
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
    public void appAskWithEmptyTextToSpeechAndNoInputPrompts() throws Exception {
        Response response = app.ask("", new String[] {"Can you say that again ?", "What ?"});
        assertNull(response);
    }

    @Test
    public void appAskWithNullTextToSpeechAndNoInputPrompts() throws Exception {
        String textToSpeech = null;
        Response response = app.ask(textToSpeech, new String[] {"Can you say that again ?", "What ?"});
        assertNull(response);
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
    public void appAskWithSimpleResponseWithNoInputPrompt()throws Exception {
        final String SPEECH = "Hello";
        final String DISPLAYTEXT = "Hi";
        String [] noInputPrompt = {"Say again", "one more time"};
        SimpleResponse simpleResponse = new SimpleResponse(SPEECH, DISPLAYTEXT);
        Response response = app.ask(simpleResponse,noInputPrompt);
        assertNotNull(response);
        assertTextToSpeech(response, SPEECH);
        assertDisplayText(response, DISPLAYTEXT);
        assertExpectUserResponseTrue(response);
        assertNoInputPromptTexttoSpeech(response,noInputPrompt);
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
    public void appAskWithRichResponseWithNoInputPrompt()throws Exception {
        RichResponse richResponse = app.buildRichResponse()
                .addSimpleResponse(new SimpleResponse("hello", "hi"))
                .addSuggestions(Arrays.asList("say this", "or this"));
        String [] noInputPrompt = {"Say again", "one more time"};
        Response response = app.ask(richResponse, noInputPrompt);
        assertNotNull(response);
        assertSpeech(response, "hello");
        assertExpectUserResponseTrue(response);
        assertNotNullRichResponse(response);
        assertTextToSpeech(response,"hello");
        assertDisplayText(response, "hi");
        assertSuggestions(response, "say this", "or this");
        assertNoInputPromptTexttoSpeech(response,noInputPrompt);
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
        Response response = app.askWithList(null, new ListSelect());
        assertNull(response);
    }

    @Test
    public void askWithListNullList() throws Exception {
        Response response = app.askWithList(new Object(), null);
        assertNull(response);
    }

    @Test
    public void askWithListWithListSizeInvalid() throws Exception {
        ListSelect listSelect = new ListSelect();
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item());
        listSelect.setItems(itemList);
        Response response = app.askWithList(new Object(), listSelect);
        assertNull(response);
    }

    @Test
    public void askWithListWithInvalidInputPrompt() throws Exception {
        assertNull(app.askWithList(1, getListSelect()));
    }

    @Test
    public void askWithListWhereInputPromptParamIsString() throws Exception {
        Response response = app.askWithList("Hello", getListSelect());
        assertNotNull(response);
        assertSpeech(response, "Hello");
        assertExpectUserResponseTrue(response);
        assertIsSsmlFalse(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertListSelect(response, getListSelect());
    }

    @Test
    public void askWithListWhereInputPromptParamIsStringAsSSML() throws Exception {
        Response response = app.askWithList("<speak>Hello</speak>", getListSelect());
        assertNotNull(response);
        assertSpeech(response, "<speak>Hello</speak>");
        assertExpectUserResponseTrue(response);
        assertIsSsmlTrue(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertListSelect(response, getListSelect());

    }

    @Test
    public void askWithListWhereInputPromptParamIsEmptyString() throws Exception {
        Response response = app.askWithList("", getListSelect()) ;
        assertNull(response);
    }

    @Test
    public void askWithListWhereInputPromptParamIsSimpleResObj() throws Exception {
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setDisplayText("Hello");
        simpleResponse.setTextToSpeech("Hi");
        Response response = app.askWithList(simpleResponse, getListSelect());
        assertNotNull(response);
        assertTextToSpeech(response, "Hi");
        assertDisplayText(response, "Hello");
        assertExpectUserResponseTrue(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertListSelect(response, getListSelect());
    }

    @Test
    public void askWithListWhereInputPromptParamIsSimpleResObjWithSSML() throws Exception {
        SimpleResponse simpleResponse = new SimpleResponse("<speak>Hi</speak>", "Hello");
        Response response = app.askWithList(simpleResponse, getListSelect());
        assertNotNull(response);
        assertSsmlText(response, "<speak>Hi</speak>");
        assertDisplayText(response, "Hello");
        assertExpectUserResponseTrue(response);
        //assertIsSsmlTrue(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertListSelect(response, getListSelect());
    }

    @Test
    public void askWithListWhereInputPromptParamIsRichResObj() throws Exception {
        RichResponse richResponse = new RichResponse();
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setDisplayText("Hello");
        simpleResponse.setTextToSpeech("Hi");
        richResponse.addSimpleResponse(simpleResponse);
        richResponse.addSuggestions("one", "two", "three");
        Response response = app.askWithList(richResponse, getListSelect());
        assertNotNull(response);
        assertTextToSpeech(response, "Hi");
        assertDisplayText(response, "Hello");
        assertSuggestions(response, "one", "two", "three");
        assertExpectUserResponseTrue(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertListSelect(response, getListSelect());
    }

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
    public void askWithCarouselWithNullInputPrompt() throws Exception {
        assertNull(app.askWithCarousel(null, getCarousel()));
    }

    @Test
    public void askWithCarouselWithNullList() throws Exception {
        assertNull(app.askWithCarousel(new Object(), null));
    }

    @Test
    public void askWithCarouselWithEmptyInputPromptString() throws Exception {
        assertNull(app.askWithCarousel("", getCarousel()));
    }

    @Test
    public void askWithCarouselWithInvalidListSize() throws Exception {
        Carousel carousel = new Carousel();
        List<Item> itemList = new ArrayList<>();
        itemList.add(new Item());
        carousel.setItems(itemList);
        assertNull(app.askWithCarousel(new Object(), carousel));
    }

    @Test
    public void askWithCarouselWhereInputPromptIsString() throws Exception {
        Response response = app.askWithCarousel("Hello", getCarousel());
        assertNotNull(response);
        assertSpeech(response,"Hello");
        assertNotNull(response.getData());
        assertNotNull(response.getData().getGoogle());
        assertExpectUserResponseTrue(response);
        assertIsSsmlFalse(response);
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertCarousel(response, getCarousel());
    }

    @Test
    public void askWithCarouselWhereInputPromptParamIsStringAsSSML() throws Exception {
        Response response = app.askWithCarousel("<speak>Hello</speak>", getCarousel());
        assertNotNull(response);
        assertSpeech(response,"<speak>Hello</speak>");
        assertNotNull(response.getData());
        assertNotNull(response.getData().getGoogle());
        assertExpectUserResponseTrue(response);
        assertIsSsmlTrue(response);
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertCarousel(response, getCarousel());
    }

    @Test
    public void askWithCarouselWhereInputPromptParamIsSimpleResObj() throws Exception {
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setDisplayText("Hello");
        simpleResponse.setTextToSpeech("Hi");
        Response response = app.askWithCarousel(simpleResponse, getCarousel());
        assertNotNull(response);
        assertTextToSpeech(response, "Hi");
        assertDisplayText(response, "Hello");
        assertExpectUserResponseTrue(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertCarousel(response, getCarousel());
    }

    @Test
    public void askWithCarouselWhereInputPromptParamIsSimpleResObjWithSSML() throws Exception {
        SimpleResponse simpleResponse = new SimpleResponse("<speak>Hi</speak>", "Hello");
        Response response = app.askWithCarousel(simpleResponse, getCarousel());
        assertNotNull(response);
        assertSsmlText(response, "<speak>Hi</speak>");
        assertDisplayText(response, "Hello");
        assertExpectUserResponseTrue(response);
        //assertIsSsmlTrue(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertCarousel(response, getCarousel());
    }

    @Test
    public void askWithCarouselWhereInputPromptParamIsRichResObj() throws Exception {
        RichResponse richResponse = new RichResponse();
        SimpleResponse simpleResponse = new SimpleResponse();
        simpleResponse.setDisplayText("Hello");
        simpleResponse.setTextToSpeech("Hi");
        richResponse.addSimpleResponse(simpleResponse);
        richResponse.addSuggestions("one", "two", "three");
        Response response = app.askWithCarousel(richResponse, getCarousel());
        assertNotNull(response);
        assertTextToSpeech(response, "Hi");
        assertDisplayText(response, "Hello");
        assertSuggestions(response, "one", "two", "three");
        assertExpectUserResponseTrue(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertOptionIntent(response);
        assertOptionSystemIntentData(response);
        assertCarousel(response, getCarousel());
    }

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

    // *********** askForPermissions **********

    /*
    askForPermissions(string, ListSelect<String>
        1. Null context
        2. Null list
        3. Empty ListSelect
        4. Invalid Permission Type
        5. HappyPath
     */

    @Test
    public void askForPermissionsWithNullContext() throws Exception {
        assertNull(app.askForPermissions(null, new ArrayList<>()));
    }

    @Test
    public void askForPermissionsWithNullListParam() throws Exception {
        assertNull(app.askForPermissions("String", null));
    }

    @Test
    public void askForPermissionsWithEmptyListParam() throws Exception {
        assertNull(app.askForPermissions("String", new ArrayList<>()));
    }

    @Test
    public void askForPermissionsWithInvalidPermissionType() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Invalid");
        assertNull(app.askForPermissions("String", list));
    }

    @Test
    public void askForPermissionsWithInvalidAndValidPermissionType() throws Exception {
        List<String> list = new ArrayList<>();
        list.add("Invalid");
        list.add(DialogflowApp.SupportedPermissions.DEVICE_PRECISE_LOCATION);
        assertNull(app.askForPermissions("String", list));
    }

    @Test
    public void askForPermissionsHappyPath() throws Exception {
        List<String> list = new ArrayList<>();
        list.add(DialogflowApp.SupportedPermissions.DEVICE_PRECISE_LOCATION);
        list.add(DialogflowApp.SupportedPermissions.NAME);
        Response response = app.askForPermissions("To do this", list);
        assertNotNull(response);
        assertSpeech(response,"PLACEHOLDER_FOR_PERMISSION");
        assertExpectUserResponseTrue(response);
        assertIsSsmlFalse(response);
        assertPermissionIntent(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertPermissionSystemIntentData(response);
        assertEquals("Permissions", response.getData().getGoogle().getSystemIntent().getData().getPermissions(), list);
        assertEquals("Opt Context", response.getData().getGoogle().getSystemIntent().getData().getOptContext(), "To do this");
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
        assertNull(app.askForPermission(null, "String"));
    }

    @Test
    public void askForPermissionWithNullPermission() throws Exception {
        assertNull(app.askForPermission("String", null));
    }
    @Test
    public void askForPermissionWithInvalidPermissionType() throws Exception {
        assertNull(app.askForPermission("Sting", "Invalid"));
    }

    @Test
    public void askForPermissionHappyPath() throws Exception {
        Response response = app.askForPermission("To do this", AssistantApp.SupportedPermissions.NAME);
        assertNotNull(response);
        assertSpeech(response,"PLACEHOLDER_FOR_PERMISSION");
        assertExpectUserResponseTrue(response);
        assertIsSsmlFalse(response);
        assertPermissionIntent(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertPermissionSystemIntentData(response);
        assertEquals("Permissions", response.getData().getGoogle().getSystemIntent().getData().getPermissions().get(0), AssistantApp.SupportedPermissions.NAME);
        assertEquals("Opt Context", response.getData().getGoogle().getSystemIntent().getData().getOptContext(), "To do this");
    }

    @Test
    public void fulfillPermissionsRequestTest() throws Exception {
        AssistantApp assistantApp = new AssistantApp();
        assertNull(assistantApp.fulfillPermissionsRequest(new SystemIntentData()));
    }

    @Test
    public void askForConfirmation() throws Exception {
        Response response = app.askForConfirmation("Hello", null);
        assertNotNull(response);
        assertSpeech(response,"PLACEHOLDER_FOR_CONFIRMATION");
        assertExpectUserResponseTrue(response);
        assertIsSsmlFalse(response);
        assertConfirmationIntent(response);
        assertNotNull(response.getData().getGoogle().getSystemIntent());
        assertConfirmationSystemIntentData(response);
        assertEquals(response.getData().getGoogle().getSystemIntent().getData().getDialogSpec().getRequestConfirmationText(), "Hello");
    }

    @Test
    public void askForConfirmationWithNullPrompt() throws Exception {
        Response response = app.askForConfirmation(null, null);
        assertNull(response);
    }

    private ListSelect getListSelect() {
        return app.buildList("Test Title")
                .addItems(
                        app.buildOptionItem("Test 1",
                                new String [] {"test 1", "test ONE", "test i", "one"})
                                .setDescription("Test Description")
                                .setImage("www.test.com", "text accessibility", 0,0)
                                .setTitle("test item title")
                )
                .addItems(
                        app.buildOptionItem("Test 2",
                                new String [] {"test 2", "test two", "test ii", "two"})
                                .setDescription("Test Description")
                                .setImage("www.test.com", "text accessibility", 0,0)
                                .setTitle("test item title")
                );
    }

    private Carousel getCarousel() {
        return app.buildCarousel()
                .addItems(
                        app.buildOptionItem("Test 1",
                                new String [] {"test 1", "test ONE", "test i", "one"})
                                .setDescription("Test Description")
                                .setImage("www.test.com", "text accessibility", 0,0)
                                .setTitle("test item title")
                )
                .addItems(
                        app.buildOptionItem("Test 2",
                                new String [] {"test 2", "test two", "test ii", "two"})
                                .setDescription("Test Description")
                                .setImage("www.test.com", "text accessibility", 0,0)
                                .setTitle("test item title")
                );
    }
}
