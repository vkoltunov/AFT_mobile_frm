package api.apps.PhotoLab.text;

import api.android.Android;
import api.apps.PhotoLab.result.Result;
import api.apps.PhotoLab.save.Save;
import api.interfaces.Activity;
import core.MyLogger;
import org.apache.xpath.operations.And;

/**
 * Created by User on 5/3/2017.
 */
public class Text implements Activity {

    public TextUiObjects uiObject = new TextUiObjects();

    @Override
    public Text waitToLoad(){
        try{
            MyLogger.log.info("Waiting for Text page");
            uiObject.text().waitToAppear(5);
            return Android.app.photoLab.text;
        }catch (AssertionError e) {
            throw new AssertionError("Text page failed to load/open");
        }
    }

    public Text tapNext(){
        try{
            MyLogger.log.info("Tap Next button for Text page.");
            uiObject.next().tap();
            return Android.app.photoLab.text.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Next button for Text page failed to tap.");
        }
    }

    public Result tapDone(){
        try{
            MyLogger.log.info("Tap Done button for Text page.");
            uiObject.done().waitToAppear(10).tap();
            return Android.app.photoLab.result.waitToLoad();
        }catch (AssertionError e) {
            throw new AssertionError("Done button for Text page failed to tap.");
        }
    }

    public void typeText(String text){
        try{
            MyLogger.log.info("Type text for Text page.");
            uiObject.addYourText().typeText(text);
        }catch (AssertionError e) {
            throw new AssertionError("Set text for Text page failed to type.");
        }
    }

    public void tapEdit(){
        try{
            MyLogger.log.info("Tap Edit option for Text page.");
            uiObject.edit().waitToAppear(10).tap();
            uiObject.editText().waitToAppear(10);
        }catch (AssertionError e) {
            throw new AssertionError("Edit option for Text page failed to tap.");
        }
    }

    public void tapFont(){
        try{
            MyLogger.log.info("Tap Font option for Text page.");
            uiObject.font().waitToAppear(10).tap();
            uiObject.editText().waitToAppear(10);
        }catch (AssertionError e) {
            throw new AssertionError("Font option for Text page failed to tap.");
        }
    }

    public void tapColor(){
        try{
            MyLogger.log.info("Tap Color option for Text page.");
            uiObject.color().waitToAppear(10).tap();
            uiObject.editText().waitToAppear(10);
        }catch (AssertionError e) {
            throw new AssertionError("Color option for Text page failed to tap.");
        }
    }

    public void tapStyle(){
        try{
            MyLogger.log.info("Tap Style option for Text page.");
            uiObject.style().waitToAppear(10).tap();
            uiObject.editText().waitToAppear(10);
        }catch (AssertionError e) {
            throw new AssertionError("Style option for Text page failed to tap.");
        }
    }

    public void tapOpacity(){
        try{
            MyLogger.log.info("Tap Opacity option for Text page.");
            uiObject.opacity().waitToAppear(10).tap();
            uiObject.editText().waitToAppear(10);
        }catch (AssertionError e) {
            throw new AssertionError("Opacity option for Text page failed to tap.");
        }
    }

    public void selectOption(int index){
        try{
            MyLogger.log.info("Select Option with index '"+index+"'.");
            uiObject.by_index(index).waitToAppear(10).tap();
            uiObject.editText().waitToAppear(10);
        }catch (AssertionError e) {
            throw new AssertionError("Option with index '"+index+"' for Text page failed to tap.");
        }
    }
}
