import React from "react";
import { Card } from "antd";
import { SparklesCore } from "./Sparkles";

const genres = [
  { title: "Pop", src: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" },
  { title: "Rock", src: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" },
  { title: "Jazz", src: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" },
  { title: "Classical", src: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" },
  { title: "Hip-Hop", src: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" },
  { title: "Electronic", src: "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png" },
];

const Discover = () => {
  return (
    <div className="m-2">
      <div className="p-2">
        <h1 className="font-semibold text-4xl tracking-widest">discover</h1>
      </div>
      <div className="grid grid-cols-2 gap-4">
        {genres.map((genre) => (
          <Card
            key={genre.title}
            hoverable
            className="w-full"
            cover={
              <div className="w-full h-40 overflow-hidden">
                <img alt={genre.title} src={genre.src} className="object-cover w-full h-full" />
              </div>
            }
          >
            <Card.Meta title={genre.title} />
          </Card>
        ))}
      </div>
    </div>
  );
};

export default Discover;