import numpy as np
import matplotlib.pyplot as plt

# Calculate Predator next value.
def predator(x, y, a, b, e):
	return x + a*x + b*y + e*x*x

# Calculate Prey next value.
def prey(x, y, c, d, f):
	return y + c*x + d*y + f*y*y

# Plot phase plot.
def plot(title, figure, x, y):

	plt.figure(figure)
	plt.title(title)
	plt.xlabel("Time")
	plt.ylabel("Population")
	plt.plot(x)
	plt.plot(y)

# Iterate and use the values provided to produce a stable osscilation.
def iterate(a, iterations):

	x = np.zeros(iterations + 1, dtype=float)
	y = np.zeros(iterations + 1, dtype=float)

	x[0] = 1000.0
	y[0] = 500.0

	# Set values for all constants to produce a stable osscilation.
	b = -3
	e = 0.0000001
	c = 0.3
	d = -2
	f = 0.00001

	# Iterate to calculate next states.
	for i in range(0, iterations):
		x[i + 1] = predator(x[i], y[i], a, b, e)
		y[i + 1] = prey(x[i], y[i], c, d, f)


	return (x, y)

def iterate_extended_terms(a, iterations):

	x = np.zeros(iterations + 1, dtype=float)
	y = np.zeros(iterations + 1, dtype=float)

	x[0] = 1000.0
	y[0] = 500.0

	# Set values for all constants to produce a stable osscilation.
	b = -3
	e = 0.0000001
	g = 0
	c = 0.3
	d = -2
	f = 0.00001
	h = 0

	# extended terms
	g = 0.00001
	h = 0.00001

	for i in range(0, iterations):
		x[i + 1] = predator(x[i], y[i], a, b, e) + g * x[i] * y[i]
		y[i + 1] = prey(x[i], y[i], c, d, f) + h * x[i] * y[i]

	return (x, y)

# Calculate the mean of Predator and Prey.
def mean(x, y):
	return (np.sum(x)/x.size, np.sum(y)/y.size)

def solution():

	# ******** Equations without the extended terms g and h are used for the following solutions **********

	# Number of iterations.
	iterations = 1000

	# Setting the value of a
	a = 0.6
	x, y = iterate(a, iterations)
	print("**** Plotting the stable Oscillation without the extended terms g and h. You would be able to see it on the screen. ****")
	plot("Stable Oscillation without extended terms g and h.", 1, x, y)

	# Calculate mean x and mean y using a = 0.6. 
	print("Mean with a = 0.6")
	meanx, meany = mean(x, y)
	print("Mean Predator x: " + str(meanx))
	print("Mean Prey y: " + str(meany))
	print("")

	## Calculate mean x and mean y using a = 0.59. To see impact of decreasing a on mean x and mean y.
	a = 0.59
	x, y = iterate(a, iterations)

	print("Mean with a = 0.59")
	meanx, meany = mean(x, y)
	print("Mean Predator x: " + str(meanx))
	print("Mean Prey y: " + str(meany))
	print("")

	## Calculate mean x and mean y using a = 0.61. To see impact of increasing a on mean x and mean y.
	a = 0.61
	x, y = iterate(a, iterations)

	print("Mean with a = 0.61")
	meanx, meany = mean(x, y)
	print("Mean Predator x: " + str(meanx))
	print("Mean Prey y: " + str(meany))
	print("")

	# Print observations.
	print("**** MEAN Observations Result ****")
	print("As you can see that on increasing a the mean population of both Predator and Prey gets decreased, and on decreasing a the mean population of both gets increased.")

	# *********************************************************************************************

def solution_extended_terms():

	# ******** Equations with the extended terms g and h are used for the following experiments **********

	# Number of iterations.
	iterations = 1000

	# Setting the value of a
	a = 0.6
	x, y = iterate_extended_terms(a, iterations)
	print("**** Plotting the stable Oscillation with the extended terms g and h. You would be able to see it on the screen. ****")
	plot("Stable Oscillation with extended terms g and h.", 2, x, y)

	# Calculate mean x and mean y using a = 0.6. 
	print("Mean with a = 0.6")
	meanx, meany = mean(x, y)
	print("Mean Predator x: " + str(meanx))
	print("Mean Prey y: " + str(meany))
	print("")

	## Calculate mean x and mean y using a = 0.59. To see impact of decreasing a on mean x and mean y.
	a = 0.59
	x, y = iterate_extended_terms(a, iterations)

	print("Mean with a = 0.59")
	meanx, meany = mean(x, y)
	print("Mean Predator x: " + str(meanx))
	print("Mean Prey y: " + str(meany))
	print("")

	## Calculate mean x and mean y using a = 0.61. To see impact of increasing a on mean x and mean y.
	a = 0.61
	x, y = iterate_extended_terms(a, iterations)

	print("Mean with a = 0.61")
	meanx, meany = mean(x, y)
	print("Mean Predator x: " + str(meanx))
	print("Mean Prey y: " + str(meany))
	print("")

	## Print observations.
	print("**** MEAN Observations Result ****")
	print("As you can see that on increasing a the mean population of both Predator and Prey gets decreased, and on decreasing a the mean population of both gets increased.")


	# *********************************************************************************************

if __name__== "__main__":

	# Solution with equations without using extended terms g and h.
	solution()

	print("")

	# Solution with equations using extended terms g and h.
	solution_extended_terms()

	# Plot both the graphs
	plt.show()

	print("")

	# Print result of final observations of both the systems.
	print("**** Result from both the systems ****")
	print("As you can see in the results above from both the systems that with the extended terms g and h the population size is decreased as compared to when they are no extended terms.")










