// const loginBtn = document.getElementById('login');
// const signupBtn = document.getElementById('signup');
//
// loginBtn.addEventListener('click', (e) => {
//     let parent = e.target.parentElement;
//     parent.classList.toggle("slide-up")
//     if (!parent.classList.contains("slide-up")) {
//         signupBtn.parentElement.parentElement.classList.toggle('slide-up')
//     }
// });
//
// signupBtn.addEventListener('click', (e) => {
//     let parent = e.target.parentElement.parentElement;
//     parent.classList.toggle("slide-up")
//     if (!parent.classList.contains("slide-up")) {
//         loginBtn.parentElement.classList.toggle('slide-up')
//     }
// });

window.addEventListener('click', function (e) {
    const select = document.querySelector('.custom-select')
    if (!select.contains(e.target)) {
        select.classList.remove('open');
    }
});