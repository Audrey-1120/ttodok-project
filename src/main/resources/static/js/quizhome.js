const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const card = urlParams.get('card');

console.log("card", card);

document.getElementById("goQuiz1").addEventListener("click", function(event) {
            const card1 = card;
            console.log("card1", card1);
            redirectToTextLearningPage(card1);
    });

function redirectToTextLearningPage() {
                console.log("redirecting with card", card);
                const apiUrl = `/quiz1?card=${card}`;
                window.location.href = apiUrl;
            }