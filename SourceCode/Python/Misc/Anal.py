
#/* *** ODSATag: Analp1 *** */
sum = 0
for i in range(n):
    for j in range(n):
        sum += 1
#/* *** ODSAendTag: Analp1 *** */

#/* *** ODSATag: c3p2 *** */
a = b
#/* *** ODSAendTag: c3p2 *** */

#/* *** ODSATag: c3p3 *** */
sum = 0
for i in range(n):
    sum += n
#/* *** ODSAendTag: c3p3 *** */

#/* *** ODSATag: c3p4 *** */
sum = 0
for j in range(n):     # First for loop
    for i in range(j): # is a double loop
        sum += 1
for k in range(n):     # Second for loop
    A[k] = k
#/* *** ODSAendTag: c3p4 *** */

#/* *** ODSATag: c3p5 *** */
sum1 = 0
for i in range(n):     # First double loop
    for j in range(n): # do n times
        sum1 += 1

sum2 = 0
for i in range(n)      # Second double loop
    for j in range(i): # do i times
        sum2 += 1
#/* *** ODSAendTag: c3p5 *** */

#/* *** ODSATag: c3p6 *** */
sum1 = 0
k = 1
while k <= n:          # Do log n times
    for j in range(n): # do n times
        sum1 += 1
    k *= 2

sum2 = 0
k = 1
while k <= n:          # Do log n times
    for j in range(k): # do k times
        sum2 += 1
    k *= 2
#/* *** ODSAendTag: c3p6 *** */

#/* *** ODSATag: c3p16 *** */
for i in range(C):            # Initialize count
    count[i] = 0
    for i in range(P):        # Look at all of the pixels
        count[value(i)] += 1  # Increment a pixel value count
    sort(count)               # Sort pixel value counts
#/* *** ODSAendTag: c3p16 *** */

#def collatz(int n):
#/* *** ODSATag: Collatz *** */
while n > 1:
    if ODD(n):
        n = 3 * n + 1
    else:
        n = n / 2
#/* *** ODSAendTag: Collatz *** */
