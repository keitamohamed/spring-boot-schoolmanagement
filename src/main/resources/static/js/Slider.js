let slideIndex = 1;
showSlides(slideIndex);

(function () {
    let slides = document.getElementsByClassName("mySlides");
    if (slideIndex <= slides.length) {
        setInterval(function () {
            showSlides(slideIndex)
            slideIndex++;
        }, 6000);
    }
})();

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    let i = 0;
    let slides = document.getElementsByClassName("mySlides");
    let dots = document.getElementsByClassName("dot");
    if (n > slides.length) {
        slideIndex = 1
    }
    if (n < 1) {
        slideIndex = slides.length
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";

    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    console.log("<br> ", slideIndex - 1)
    slides[(slideIndex - 1)].style.display = "block";
    console.log(slideIndex)

}