# Electronic Watch

For the given value of seconds since midnight, return the electronic watch screen output.

Input value is given via `System.in`. Output value must be printed to `System.out`

It is guaranteed, that input number is non-negative.

Output format is `h:mm:ss` \(possible values: \[0:00:00; 23:59:59\]\).

*Extra challenge*: try to solve the task without using `if` statements or cycles.

## Examples

---
Input: `60`

Output: `0:01:00`

---
Input: `3599`

Output: `0:59:59`

---
Input: `86229`

Output: `23:57:09`

---
Input: `86400`

Output: `0:00:00`

---
Input: `89999`

Output: `0:59:59`

---
Input: `86460`

Output: `0:01:00`

---