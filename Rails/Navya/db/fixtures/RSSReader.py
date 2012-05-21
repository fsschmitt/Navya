import urllib2
import os
from bs4 import BeautifulSoup
import time

def getNews() :
	global soup
	for item in soup.findAll('item'):
		global val, newpath, newpath_string
		f = open(newpath_string+'%i.txt' % (val+time.time()),'w+')
		desc = item.description.string
		num = desc.find("<")
		if len(desc[0:num]) != 0:
			f.write(item.title.string.encode('UTF-8')+'\n')
			f.write(item.link.string.encode('UTF-8')+'\n')
			f.write(desc[0:num].encode('UTF-8'))
			val = val + 1
	return

val = 1
newpath = r'NoticiasNovas/'
newpath_string = 'NoticiasNovas/'
if not os.path.exists(newpath): os.makedirs(newpath)


soup = BeautifulSoup(urllib2.urlopen('http://feeds.feedburner.com/PublicoRSS?format=xml').read())
getNews()

soup = BeautifulSoup(urllib2.urlopen('http://noticias.sapo.pt/rss/news/').read())
getNews()

soup = BeautifulSoup(urllib2.urlopen('http://sol.sapo.pt/rss/').read())
getNews()

soup = BeautifulSoup(urllib2.urlopen('http://services.sapo.pt/RSS/Feed/sapo/desporto/teasers').read())
getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Desporto').read())
getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Cultura').read())
getNews()

soup = BeautifulSoup(urllib2.urlopen('http://feeds.jn.pt/JN-Tecnologia').read())
getNews()