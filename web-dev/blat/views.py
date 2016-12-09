from django.shortcuts import render
from django.views import generic
from django.contrib.auth.decorators import login_required
from django.utils.decorators import method_decorator
from .models import Blat
# Create your views here.

class IndexView(generic.ListView):
	template_name = 'blat/home.html'
	context_object_name = 'blat_list'
	
	def get_queryset(self):
		return Blat.objects.order_by('-created_on')[:20]

class DetailView(generic.DetailView):
	model = Blat
	template_name = 'blat/detail.html'
	contex_object_name = 'blat'

class MyView(IndexView):
	def get_query(self):
		return Blat.objects.filter(created_by=self.request.user.id).order_by(-'created_on')[:20]
	

	@method_decorator(login_required)
	def dispatch(self, *args, **kwargs):
		return super(MyView, self).dispatch(*args, **kwargs)
	
