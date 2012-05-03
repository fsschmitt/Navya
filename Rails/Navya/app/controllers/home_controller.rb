class HomeController < ApplicationController
  def index
  	@ls = `ls`
  	@economy_politics= ["news1","news2","news3","news4","news5","news 6"]
  end
end
