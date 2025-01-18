"use client";
import React from "react";
import { useSearchParams } from "next/navigation";
import MobileNav from "@/components/MobileNav";
import Discover from "@/components/Discover";
import Artists from "@/components/Artists";
import Search from "@/components/Search";
import Albums from "@/components/Albums";
import Playlists from "@/components/Playlists";

const HomePage = () => {
    const searchParams = useSearchParams();
    const userId = searchParams.get("userId");

    const [activeTab, setActiveTab] = React.useState("discover");

    const renderContent = () => {
        switch (activeTab) {
            case "discover":
                return <Discover />;
            case "artists":
                return <Artists />;
            case "search":
                return <Search />;
            case "albums":
                return <Albums />;
            case "playlists":
                return <Playlists />;
        }
    };

    return (
        <div className="relative min-h-screen min-w-full">
            <div className="absolute top-0 w-full h-full overflow-auto">
                {renderContent()}
            </div>
            <div className="fixed bottom-0 left-0 w-full">
                <MobileNav onNavClick={setActiveTab} />
            </div>
        </div>
    );
};

export default HomePage;