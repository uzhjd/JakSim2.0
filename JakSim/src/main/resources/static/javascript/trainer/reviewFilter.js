  const selectElement = document.getElementById('review-filter');
  const reviewContainer = document.getElementById('review-container');

  selectElement.addEventListener('change', function() {
    // Get the selected filter value
    const selectedFilter = this.value;

    // Check if the review container element exists
    if (reviewContainer) {
      // Sort the review elements based on the selected filter
      const reviews = Array.from(reviewContainer.getElementsByClassName('review'));
      reviews.sort(function(a, b) {
        if (selectedFilter === 'highest') {
          return b.querySelectorAll('.fa-star').length - a.querySelectorAll('.fa-star').length;
        } else if (selectedFilter === 'lowest') {
          return a.querySelectorAll('.fa-star').length - b.querySelectorAll('.fa-star').length;
        } else if (selectedFilter === 'recent') {
          const dateA = new Date(a.querySelector('.review-date').textContent);
          const dateB = new Date(b.querySelector('.review-date').textContent);
          return dateB - dateA;
        }
      });

      // Reorder the review elements in the container
      reviews.forEach(function(review) {
        reviewContainer.appendChild(review);
      });
    }
  });

//function deleteReview(reviewId) {
//  axios.delete(`/deleteReview/${reviewId}`)
//    .then(function (response) {
//      // Handle the successful response
//      console.log(response);
//    })
//    .catch(function (error) {
//      // Handle the error
//      console.log(error);
//    });
//}


