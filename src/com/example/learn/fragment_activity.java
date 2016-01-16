package com.example.learn;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class fragment_activity extends FragmentActivity{
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment);
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		LeftFragment left = new LeftFragment();
		RightFragment right = new RightFragment();
		ft.add(R.id.left_layout, left);
		ft.add(R.id.right_layout, right);
		ft.commit();
	}
	
	@SuppressLint("ValidFragment")
	public class RightFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			return inflater.inflate(R.layout.right_fragment, container, false);
		}
	}
	@SuppressLint("ValidFragment")
	public class LeftFragment extends Fragment{
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
			return inflater.inflate(R.layout.left_fragment, container, false);
		}
	}
}


