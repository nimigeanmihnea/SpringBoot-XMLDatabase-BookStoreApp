/**
 * Created by MIHONE on 4/8/2017.
 */
$('.login-input').on('focus', function() {
    $('.login').addClass('focused');
});

// $('.login').on('submit', function(e) {
//     e.preventDefault();
//     $('.login').removeClass('focused').addClass('loading');
//     e.setTimeout(function () {
//         $('.login').removeClass('loading').addClass('loaded');
//         $('.login').submit();
//     },3000);
// });
