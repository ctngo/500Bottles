(function() {
    window._500bottles.views = window._500bottles.views || {};
    var $ns = window._500bottles;

    var VIEW_CONTAINER = "view_container";

    var NO_DISPLAY = "no_display";
    var BLACKOUT_ANIM_IN = "fadeIn";
    var BLACKOUT_ANIM_OUT = "fadeOut";
    var BLACKOUT_TIMER = 1000;

    $ns.views.setBlackout = function()
    {
        var blackout = document.getElementById("blackout");

        if (blackout == null)
            $("body").prepend($("<div/>").attr("id", "blackout").addClass("animated"));

        blackout = document.getElementById("blackout");

        $(blackout).removeClass(NO_DISPLAY);
        $(blackout).removeClass(BLACKOUT_ANIM_OUT);
        $(blackout).addClass(BLACKOUT_ANIM_IN);
    }

    $ns.views.clearBlackout = function()
    {
        var blackout = document.getElementById("blackout");

        if (blackout == null)
            return;

        $(blackout).removeClass(BLACKOUT_ANIM_IN);
        $(blackout).addClass(BLACKOUT_ANIM_OUT);

        setTimeout(function() {
            $(blackout).addClass(NO_DISPLAY);
        }, BLACKOUT_TIMER);
    }

    /**
     * Adds the specified content to the main view container.
     * @param view_contents
     */
    $ns.views.add_view = function(data)
    {
        var view_contents = data.contents;
        var id = data.id;
        var view_container = document.getElementById(VIEW_CONTAINER);

        $(view_container).append(view_contents);

        var child = document.getElementById(id);
        $ns.views.fix_height(view_container, child);
    }

    /**
     *
     * @param parent
     * @param contents
     * @param subview_id
     */
    $ns.views.add_subview = function(parent, contents, subview_id)
    {
        $(parent).append(contents);

        var subview = document.getElementById(subview_id);
        $ns.anim.animate_in({element: subview});
        $ns.views.fix_height(parent, subview);
    }

    /**
     * Fixes the body and view container height to accommodate the size of
     * the new content.
     * @param element
     */
    $ns.views.fix_body_height = function(element)
    {
        var header_height = $("header").outerHeight(true);
        var content_height = $(element).outerHeight(true);
        var new_height = content_height + header_height;

        if (new_height < $(window).outerHeight(true) - header_height)
            new_height = $(window).outerHeight(true) - header_height;

        $("body, #view_container").height(new_height);
    }

    $ns.views.fix_height = function(parent, child)
    {
        var height = $(child).outerHeight(true);
        var parent_height = $(parent).outerHeight(true);
        var header_height = $("header").outerHeight(true);
        var window_height = $(window).outerHeight(true) - header_height;

        if (window_height > height) {
            $(child).height(window_height);
            height = window_height;
        }

        if (height < window_height)
            height = window_height;

        $(parent).height(height);
    }

})();