package api.apps.Vsco;

import core.UiObject;
import core.UiSelector;

/**
 * Created by User on 10/9/2017.
 */
public class VscoUiObjects {

    private static UiObject
            dialogText,
            library,
            plus,
            all,
            firsPhoto,
            loadingPhoto,
            right,
            photo,
            filters,
            filterName,
            firstFilter,
            show,
            done,
            menuMore,
            saveGalery,
            remove,
            accept;

    public UiObject library(){
        if(library == null) library = new UiSelector().className("android.widget.TextView").resourceId("com.vsco.cam:id/side_panel_library_label").makeUiObject();
        return library;
    }

    public UiObject plus(){
        if(plus == null) plus = new UiSelector().className("android.widget.ImageView").resourceId("com.vsco.cam:id/studio_header_import_button").makeUiObject();
        return plus;
    }

    public UiObject all(){
        if(all == null) all = new UiSelector().className("android.widget.ImageView").resourceId("com.vsco.cam:id/loading_image").makeUiObject();
        return all;
    }

    public UiObject firsPhoto(){
        if(firsPhoto == null) firsPhoto = new UiSelector().className("android.widget.ImageView").resourceId("com.vsco.cam:id/loading_image").index(1).makeUiObject();
        return firsPhoto;
    }

    public UiObject loadingPhoto(){
        if(loadingPhoto == null) loadingPhoto = new UiSelector().className("android.widget.ImageView").resourceId("com.vsco.cam:id/loading_image").index(0).makeUiObject();
        return loadingPhoto;
    }

    public UiObject right(){
        if(right == null) right = new UiSelector().className("android.widget.ImageButton").resourceId("com.vsco.cam:id/grid_picker_accept").makeUiObject();
        return right;
    }

    public UiObject dialogText(){
        dialogText = new UiSelector().className("android.widget.TextView").resourceId("com.vsco.cam:id/vsco_progress_dialog_text").makeUiObject();
        return dialogText;
    }

    public UiObject photo(){
        photo = new UiSelector().className("android.widget.ImageView").resourceId("com.vsco.cam:id/photo_view").index(0).makeUiObject();
        return photo;
    }

    public UiObject filterName(String name){
        filterName = new UiSelector().className("android.widget.TextView").resourceId("com.vsco.cam:id/preset_text").text(name).makeUiObject();
        return filterName;
    }

    public UiObject filters(){
        if(filters == null) filters = new UiSelector().className("android.widget.ImageView").resourceId("com.vsco.cam:id/studio_selection_menu_edit").makeUiObject();
        return filters;
    }

    public UiObject firstFilter(){
        firstFilter = new UiSelector().className("android.widget.LinearLayout").resourceId("com.vsco.cam:id/items_layout").makeUiObject();
        return firstFilter;
    }

    public UiObject show(){
        if(show == null) show = new UiSelector().className("android.widget.ImageButton").resourceId("com.vsco.cam:id/show_hide_bottom").makeUiObject();
        return show;
    }

    public UiObject done(){
        if(done == null) done = new UiSelector().className("android.widget.ImageButton").resourceId("com.vsco.cam:id/save_option").makeUiObject();
        return done;
    }

    public UiObject menuMore(){
        if(menuMore == null) menuMore = new UiSelector().className("android.widget.ImageView").resourceId("com.vsco.cam:id/studio_selection_menu_more").makeUiObject();
        return menuMore;
    }

    public UiObject saveGalery(){
        if(saveGalery == null) saveGalery = new UiSelector().className("android.widget.Button").resourceId("com.vsco.cam:id/studio_more_menu_save_gallery").makeUiObject();
        return saveGalery;
    }

    public UiObject remove(){
        if(remove == null) remove = new UiSelector().className("android.widget.Button").resourceId("com.vsco.cam:id/studio_more_menu_delete").makeUiObject();
        return remove;
    }

    public UiObject accept(){
        if(accept == null) accept = new UiSelector().className("android.widget.ImageButton").resourceId("com.vsco.cam:id/dialog_accept").makeUiObject();
        return accept;
    }

}