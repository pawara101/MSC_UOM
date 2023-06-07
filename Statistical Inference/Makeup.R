# Set the seed for reproducibility (optional)
set.seed(42)

# Define the original data set
original_data <- c(10, 12, 17, 18, 20, 21, 15)
hist(original_data, main = "Bootstrap Samples", xlab = "Values")
# Load the "boot" package
library(boot)

# Create a function to generate a bootstrap sample
generate_bootstrap_sample <- function(data, indices) {
  data[indices]
}

# Set the number of bootstrap samples
num_samples <- 1000

# Generate the bootstrap samples
bootstrap_samples <- boot(data = original_data, statistic = generate_bootstrap_sample, R = num_samples)

# Print the bootstrap samples
print(bootstrap_samples$t)

hist(bootstrap_samples$t, main = "Bootstrap Samples", xlab = "Values")


# Calculate the confidence interval for the mean
mean_ci <- boot::boot.ci(bootstrap_samples, type = "basic", conf = 0.95)$basic

# Print the confidence interval
print(mean_ci)
