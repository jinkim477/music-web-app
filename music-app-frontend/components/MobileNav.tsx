import React from "react";

const MobileNav = ({ onNavClick }) => {
	return (
		<div className="w-full mx-4">
			<ul className="menu menu-horizontal menu-lg bg-base-200 rounded-box flex justify-around">
				<li className="flex flex-col items-center justify-center" onClick={() => onNavClick("discover")}>
					<a className="p-2 pb-1" >
						<svg
							xmlns="http://www.w3.org/2000/svg"
							width="24"
							height="24"
							viewBox="0 0 24 24"
							fill="none"
							stroke="#000000"
							strokeWidth="2"
							strokeLinecap="round"
							strokeLinejoin="round"
						>
							<circle cx="12" cy="12" r="10"></circle>
							<circle cx="12" cy="12" r="3"></circle>
						</svg>
					</a>
                    <span className="text-xs p-2 pt-1">discover</span>
				</li>
				<li className="flex flex-col items-center justify-center" onClick={() => onNavClick("artists")}>
					<a className="p-2 pb-1" >
						<svg
							xmlns="http://www.w3.org/2000/svg"
							width="24"
							height="24"
							viewBox="0 0 24 24"
							fill="none"
							stroke="#000000"
							strokeWidth="2"
							strokeLinecap="round"
							strokeLinejoin="round"
						>
							<path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path>
							<circle cx="9" cy="7" r="4"></circle>
							<path d="M23 21v-2a4 4 0 0 0-3-3.87"></path>
							<path d="M16 3.13a4 4 0 0 1 0 7.75"></path>
						</svg>
					</a>
                    <span className="text-xs p-2 pt-1">artists</span>
				</li>
				<li className="flex flex-col items-center justify-center" onClick={() => onNavClick("search")}>
					<a className="p-2 pb-1" >
						<svg
							xmlns="http://www.w3.org/2000/svg"
							width="24"
							height="24"
							viewBox="0 0 24 24"
							fill="none"
							stroke="#000000"
							strokeWidth="2"
							strokeLinecap="round"
							strokeLinejoin="round"
						>
							<circle cx="11" cy="11" r="8"></circle>
							<line x1="21" y1="21" x2="16.65" y2="16.65"></line>
						</svg>
					</a>
                    <span className="text-xs p-2 pt-1">search</span>
				</li>
				<li className="flex flex-col items-center justify-center" onClick={() => onNavClick("albums")}>
					<a className="p-2 pb-1" >
						<svg
							xmlns="http://www.w3.org/2000/svg"
							width="24"
							height="24"
							viewBox="0 0 24 24"
							fill="none"
							stroke="#000000"
							strokeWidth="2"
							strokeLinecap="round"
							strokeLinejoin="round"
						>
							<rect x="3" y="3" width="7" height="7"></rect>
							<rect x="14" y="3" width="7" height="7"></rect>
							<rect x="14" y="14" width="7" height="7"></rect>
							<rect x="3" y="14" width="7" height="7"></rect>
						</svg>
					</a>
                    <span className="text-xs p-2 pt-1">albums</span>
				</li>
				<li className="flex flex-col items-center justify-center" onClick={() => onNavClick("playlists")}>
					<a className="p-2 pb-1" >
						<svg
							xmlns="http://www.w3.org/2000/svg"
							width="24"
							height="24"
							viewBox="0 0 24 24"
							fill="none"
							stroke="#000000"
							strokeWidth="2"
							strokeLinecap="round"
							strokeLinejoin="round"
						>
							<line x1="3" y1="12" x2="21" y2="12"></line>
							<line x1="3" y1="6" x2="21" y2="6"></line>
							<line x1="3" y1="18" x2="21" y2="18"></line>
						</svg>
					</a>
                    <span className="text-xs p-2 pt-1">playlists</span>
				</li>
			</ul>
		</div>
	);
};

export default MobileNav;
