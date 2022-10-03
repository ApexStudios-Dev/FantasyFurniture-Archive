Stream.of(
Block.box(1, 0, 1, 4, 2, 4),
Block.box(1, 7, 1, 4, 9, 4),
Block.box(1, 0, 12, 4, 2, 15),
Block.box(1, 7, 12, 4, 9, 15),
Block.box(12, 0, 12, 15, 2, 15),
Block.box(12, 7, 12, 15, 9, 15),
Block.box(12, 7, 1, 15, 9, 4),
Block.box(12, 0, 1, 15, 2, 4),
Block.box(2, 0, 2, 14, 9, 14),
Block.box(2, 9, 12, 14, 24, 14),
Block.box(12, 20, 11.5, 15, 24, 14.5),
Block.box(1, 20, 11.5, 4, 24, 14.5),
Block.box(2, 24, 11.5, 5, 27, 14.5),
Block.box(11, 24, 11.5, 14, 27, 14.5),
Block.box(10, 27, 11.5, 12, 29, 14.5),
Block.box(4, 27, 11.5, 6, 29, 14.5)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();