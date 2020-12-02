$(function() {

    $("a.confirmDeletion").click(function() {
        if (!confirm("confirm deletion")) return false;
    })

    if ($("#content").length) {
        ClassicEditor
            .create(document.querySelector("#content"));
    }

    if ($("#description").length) {
        ClassicEditor
            .create(document.querySelector("#description"));
    }
})

function readURL(input, idNum) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $("#imgPreview" + idNum).attr("src", e.target.result).width(100).height(100);
        }

        reader.readAsDataURL(input.files[0]);
    }
}