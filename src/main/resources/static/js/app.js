$(function() {

    $("a.confirmDeletion").click(function() {
        if (!confirm("confirm deletion")) return false;
    })
})