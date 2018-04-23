$(document).ready(function() {
    $(".demon-img").click(function() {
        console.log($(this).attr('data-demonid'));
        $.ajax({
          method: "POST",
          url: "/shootDemon",
          data: JSON.stringify({ demonId: $(this).attr('data-demonid'), weapon: "shotgun" }),
          contentType: "application/json",
          success: function() {
            location.reload();
          }
        })
    });
})

