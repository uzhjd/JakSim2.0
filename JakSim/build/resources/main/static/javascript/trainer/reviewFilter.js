
  function reviewFilter() {
    // Get the selected filter value
    const selectedFilter = this.value;

    console.log(selectedFilter);

    // Check if the review container element exists
    if (reviewContainer) {
      
      // Sort the review elements based on the selected filter
      const reviews = Array.from(reviewContainer.getElementsByClassName('review1'));
      console.log(reviews);
      
      console.log(reviewContainer);
      

      reviews.sort(function(a, b) {
        if (selectedFilter === 'highest') {
          return b.querySelectorAll('#stars-values').length - a.querySelectorAll('#stars-values').length;
        } 
        else if (selectedFilter === 'lowest') {
          return a.querySelectorAll('#stars-values').length - b.querySelectorAll('#stars-values').length;
        } 
        else if (selectedFilter === 'recent') {
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
  }
};



