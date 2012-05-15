class HomeController < ApplicationController
  def index
  	@ls = `ls`
  	@economy_politics= ["news1","news2","news3","news4","news5","news 6"]

  	@economia = News.where("category = ?", 1).limit(5)
  	@desporto = News.where("category = ?", 2).limit(5)
  	@cultura_lazer = News.where("category = ?", 3).limit(5)
  	@ciencias_tecnologia = News.where("category = ?", 4).limit(5)
  	@sociedade = News.where("category = ?", 5).limit(5)

  end

  def populate
  	@populate = `rake db:seed_fu`
  	#File.delete("db/fixtures.news.rb")
  	redirect_to "#"
  end

end
