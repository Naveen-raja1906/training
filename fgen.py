
from random import randint

def gen_word(word_length_min,word_length_max):
	''' generates words with random word length '''
	alphabets = 'abcdefghijklmnopqrstuvwxyz'
	word = ''
	for i in range(randint(word_length_min,word_length_max)):
		word = word + alphabets[randint(0,25)]
	return word

def gen_sentence(number_of_Words_min,number_of_Words_max,word_length_min,word_length_max):
	''' generates sentence with random number of words '''
	sentence = ''
	for i in range(randint(number_of_Words_min,number_of_Words_max)):
		sentence = sentence + gen_word(word_length_min,word_length_max) + ' '
	return sentence.strip() + '\n'

def gen_file(number_of_lines,number_of_Words_min,number_of_Words_max,word_length_min,word_length_max,file_name):
	''' generates file using given number of sectence '''
	myfile = open(file_name+'.txt','a')
	for i in range(number_of_lines):
		myfile.write(gen_sentence(number_of_Words_min,number_of_Words_max,word_length_min,word_length_max))
	print("File generated Successfully!!")
	myfile.close()

if __name__ == '__main__':
	print("Please provide details to generate the File")
	number_of_lines = int(input("Enter Number of lines : "))
	number_of_Words_min = int(input("Enter the Minimum number of words in a sentence : "))
	number_of_Words_max = int(input("Enter the Maximum number of words in a sentence : "))
	word_length_min = int(input("Enter the Minimum length of the word : "))
	word_length_max = int(input("Enter the Maximum length of the word : "))
	file_name = input("Enter the File Name : ")
	gen_file(number_of_lines,number_of_Words_min,number_of_Words_max,word_length_min,word_length_max,file_name)