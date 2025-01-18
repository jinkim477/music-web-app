import type { Metadata } from "next";
import "./globals.css";
import { Montserrat} from 'next/font/google';

export const metadata: Metadata = {
  title: "koeyh",
  description: "the better music app",
};

const montserrat = Montserrat({
  subsets: ['latin'], // Optimize for Latin character set
  weight: ['300', '400', '500', '600', '700'], // Include the weights you need
  variable: '--font-montserrat', // Use a CSS variable for global access
})

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" className={montserrat.variable}>
      <body>
        {children}
      </body>
    </html>
  );
}
