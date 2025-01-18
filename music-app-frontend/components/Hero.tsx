'use client'

import React from "react";

const Hero = () => {
    const handleGetStarted = async () => {
        window.location.href = "http://localhost:8080/api/auth/login";
    };

	return (
		<div>
			<div className="hero bg-base-200 min-h-screen">
				<div className="hero-content text-center">
					<div className="max-w-md">
						<h1 className="text-5xl font-bold">hi there</h1>
						<p className="py-6">
						    welcome to the next generation of music listening. catered towards true music lovers, we offer a wide range of features to enhance your listening experience.
						</p>

                        <p className="py-6">
                            click the button below to sign in or sign up.
                        </p>
						<button className="btn btn-primary" onClick={handleGetStarted}>get started</button>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Hero;
