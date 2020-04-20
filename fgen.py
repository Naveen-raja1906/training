
from random import randint


def gen_word():
	''' generates words with random word length '''
	alphabets = 'abcdefghijklmnopqrstuvwxyz'
	word = ''
	for i in range(randint(5,20)):
		word = word + alphabets[randint(0,25)]
	return word

def gen_sentence():
	''' generates sentence with random number of words '''
	sentence = ''
	for i in range(randint(1,50)):
		sentence = sentence + gen_word() + ' '
	return sentence.strip() + '\n'

def gen_file():
	''' generates file using given number of sectence '''
	myfile = open('sample.txt','a')
	num_of_sentence = 100000
	for i in range(num_of_sentence):
		myfile.write(gen_sentence())
	myfile.close()

if __name__ == '__main__':
	gen_file()