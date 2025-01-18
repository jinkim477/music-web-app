"use client";
import React from "react";
import { useSearchParams } from "next/navigation";
import MobileNav from "@/components/MobileNav";

const HomePage = () => {
	const searchParams = useSearchParams();
	const userId = searchParams.get("userId");

	const [activeTab, setActiveTab] = React.useState("discover");

	const renderContent = () => {
		switch (activeTab) {
			case "discover":
				return (
					<div>
						<h1>Discover</h1>
					</div>
				);
			case "artists":
				return (
					<div>
						<h1>Artists</h1>
					</div>
				);
			case "search":
				return (
					<div>
						<h1>Search</h1>
					</div>
				);

			case "albums":
				return (
					<div>
						<h1>Albums</h1>
					</div>
				);

			case "playlists":
				return (
					<div>
						<h1>Playlists</h1>
					</div>
				);
		}
	};

	return (
		<div className="relative min-h-screen min-w-full">
      <div className="absolute">
        {renderContent()}
      </div>

			<div className="absolute flex justify-center items-end w-full h-full pb-4">
				<MobileNav onNavClick={setActiveTab} />
			</div>
		</div>
	);
};

export default HomePage;
