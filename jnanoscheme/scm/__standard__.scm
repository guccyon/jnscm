(define (<= x y) (or (= x y)(< x y)))

(define (>= x y) (or (= x y)(> x y)))

(define (positive? n) (< 0 n)) 

(define (negative? n) (> 0 n)) 

(define (zero? n) (= 0 n)) 

(define (odd? n) (= 1 (modulo n 2)))

(define (even? n) (= 0 (modulo n 2)))

(define fib (lambda (n)
			  (if (<= n 2)
				  1
				(+ (fib (- n 1)) (fib (- n 2))))))

(define (filter func lst)
  (cond ((null? lst) '())
		((not (func (car lst))) (filter func (cdr lst)))
		(#t (cons (car lst) (filter func (cdr lst))))))

