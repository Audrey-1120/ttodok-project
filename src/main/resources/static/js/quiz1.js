const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);
const card = urlParams.get('card');

console.log("card", card);


            $(document).ready(function(){
            var card1 = card;
                $.ajax({
                    type: 'GET',
                    url: '/study/quiz?card=' + card1,
                    success: function(data) {
                        console.log("data: ", data);
                        var questionContent = data.qeustionContent;
                        var questionPassage = data.questionPassage;

                        $('.quiz1-question').text(questionContent);
                        $('.quiz1-passage').text(questionPassage);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.log(textStatus, errorThrown);

                    }
                });
            });