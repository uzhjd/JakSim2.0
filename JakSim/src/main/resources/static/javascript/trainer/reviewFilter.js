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


            function deleteReview() {
                fetch('/delete-review', { method: 'POST' })
                    .then(response => {
                        if (response.ok) {
                            // Redirect to the desired page after successful deletion
                            alert('리뷰가 삭제 되었습니다.');
                            window.location.href = '/';
                        } else {
                            // Handle any error conditions
                            console.error('Failed to delete review');
                        }
                    })
                    .catch(error => {
                        console.error('Failed to delete review', error);
                    });
            }